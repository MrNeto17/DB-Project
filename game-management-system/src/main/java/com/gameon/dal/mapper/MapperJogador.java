package com.gameon.dal.mapper;

import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.modelJPA.JogadorJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IMapper;
import com.gameon.model.Jogador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class MapperJogador implements IMapper<Jogador, Integer> {

    public Integer create(Jogador j) throws Exception {
        try (DataScope ds = new DataScope()) {
            JogadorJPA jj = Utils.toJPA(j);
            EntityManager em = ds.getEntityManager();
            em.persist(jj);
            ds.validateWork();
            return j.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Jogador read(Integer id) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            JogadorJPA jj = em.find(JogadorJPA.class, id, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return Utils.fromJPA(jj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(Jogador j) throws Exception {
        try (DataScope ds = new DataScope()) {
            JogadorJPA jj = Utils.toJPA(j);
            EntityManager em = ds.getEntityManager();
            em.flush();
            JogadorJPA jj1 = em.find(JogadorJPA.class, j.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (jj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            jj1.setEmail(j.getEmail());
            jj1.setUsername(j.getUsername());
            jj1.setEstado(JogadorJPA.Estado.valueOf(j.getEstado().name()));
            jj1.setRegiao(jj.getRegiao());
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Jogador j) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            JogadorJPA rj = em.find(JogadorJPA.class, j.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (rj == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(rj);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    /*
    public void desativarJogador(Integer id) throws Exception {
        emf = Persistence.createEntityManagerFactory("GameOn");
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Jogador j1 = em.find(Jogador.class, id, LockModeType.PESSIMISTIC_WRITE);
            if (j1 == null)
                throw new java.lang.IllegalAccessException("Jogador inexistente");
            j1.setEstado(Inativo);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public void banirJogador(Integer id) throws Exception {
        emf = Persistence.createEntityManagerFactory("GameOn");
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Jogador j1 = em.find(Jogador.class, id, LockModeType.PESSIMISTIC_WRITE);
            if (j1 == null)
                throw new java.lang.IllegalAccessException("Jogador inexistente");
            j1.setEstado(Banido);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public int totalPontosJogador(Integer id) throws Exception {
        emf = Persistence.createEntityManagerFactory("GameOn");
        em = emf.createEntityManager();
        try {
            Jogador j1 = em.find(Jogador.class, id);
            if (j1 == null)
                throw new java.lang.IllegalAccessException("Jogador inexistente");

            Query q1 = em.createQuery("SELECT SUM(p.pontuacao) FROM PartidaNormal p WHERE p.jogador.id = :id");
            q1.setParameter("id", id);
            Long totalPontosNormal = (Long) q1.getSingleResult();

            Query q2 = em.createQuery("SELECT SUM(p.pontuacao) FROM Participacao p WHERE p.jogador.id = :id");
            q2.setParameter("id", id);
            Long totalPontosMulti = (Long) q2.getSingleResult();

            if (totalPontosNormal == null)
                totalPontosNormal = 0L;

            if (totalPontosMulti == null)
                totalPontosMulti = 0L;

            return totalPontosNormal.intValue() + totalPontosMulti.intValue();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }*/
}

