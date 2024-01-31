package com.gameon.dal.mapper;

import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.modelJPA.ConversaJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IMapper;
import com.gameon.model.Conversa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class MapperConversa implements IMapper<Conversa, Integer> {

    public Integer create(Conversa c) throws Exception {
        try (DataScope ds = new DataScope()) {
            ConversaJPA cj = Utils.toJPA(c);
            EntityManager em = ds.getEntityManager();
            em.persist(cj);
            ds.validateWork();
            return c.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Conversa read(Integer id) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            ConversaJPA cj = em.find(ConversaJPA.class, id, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return Utils.fromJPA(cj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(Conversa c) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            ConversaJPA cj1 = em.find(ConversaJPA.class, c.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (cj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            cj1.setNome(c.getNome());
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Conversa c) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            ConversaJPA cj = em.find(ConversaJPA.class, c.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (cj == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(cj);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
