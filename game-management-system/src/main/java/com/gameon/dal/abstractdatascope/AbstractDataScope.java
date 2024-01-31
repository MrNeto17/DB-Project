package com.gameon.dal.abstractdatascope;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class AbstractDataScope implements AutoCloseable {

    private static final ThreadLocal<Session> threadLocal = ThreadLocal.withInitial(() -> null);
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameOn");

    boolean isMine = true;
    boolean voted = false;

    public AbstractDataScope() {
        if (threadLocal.get() == null) {
            EntityManager em = emf.createEntityManager();
            Session s = new Session();
            s.ok = true;
            s.em = em;
            threadLocal.set(s);
            em.getTransaction().begin();
            isMine = true;
        } else
            isMine = false;
    }

    public EntityManager getEntityManager() {
        return threadLocal.get().em;
    }

    @Override
    public void close() {
        if (isMine) {
            if (threadLocal.get().ok && voted) {
                threadLocal.get().em.getTransaction().commit();
            } else
                threadLocal.get().em.getTransaction().rollback();
            threadLocal.get().em.close();
            threadLocal.remove();
        } else if (!voted)
            cancelWork();
    }

    public void validateWork() {
        voted = true;
    }

    public void cancelWork() {
        threadLocal.get().ok = false;
        voted = true;
    }

    protected class Session {
        private EntityManager em;
        private boolean ok = true;

    }
}
