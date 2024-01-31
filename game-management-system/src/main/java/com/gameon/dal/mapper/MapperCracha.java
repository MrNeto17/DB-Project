package com.gameon.dal.mapper;

import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.modelJPA.CrachaJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IMapper;
import com.gameon.model.Cracha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class MapperCracha implements IMapper<Cracha, Integer> {

    public Integer create(Cracha c) throws Exception {
        try (DataScope ds = new DataScope()) {
            CrachaJPA cj = Utils.toJPA(c);
            EntityManager em = ds.getEntityManager();
            em.persist(cj);
            ds.validateWork();
            return c.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Cracha read(Integer id) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            CrachaJPA cj = em.find(CrachaJPA.class, id, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return Utils.fromJPA(cj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(Cracha c) throws Exception {
        try (DataScope ds = new DataScope()) {
            CrachaJPA cj = Utils.toJPA(c);
            EntityManager em = ds.getEntityManager();
            em.flush();
            CrachaJPA cj1 = em.find(CrachaJPA.class, c.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (cj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            cj1.setJogo(cj.getJogo());
            cj1.setNome(c.getNome());
            cj1.setLimitePontos(c.getLimitePontos());
            cj1.setUrlImagem(c.getUrlImagem());
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Cracha c) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            CrachaJPA cj = em.find(CrachaJPA.class, c.getId(), LockModeType.PESSIMISTIC_WRITE);
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
