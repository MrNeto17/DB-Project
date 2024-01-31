package com.gameon.dal.mapper;

import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.modelJPA.RegiaoJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IMapper;
import com.gameon.model.Regiao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class MapperRegiao implements IMapper<Regiao, Integer> {

    public Integer create(Regiao r) throws Exception {
        try (DataScope ds = new DataScope()) {
            RegiaoJPA rj = Utils.toJPA(r);
            EntityManager em = ds.getEntityManager();
            em.persist(rj);
            ds.validateWork();
            return r.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Regiao read(Integer id) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            RegiaoJPA rj = em.find(RegiaoJPA.class, id, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return Utils.fromJPA(rj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(Regiao r) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            RegiaoJPA rj1 = em.find(RegiaoJPA.class, r.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (rj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            rj1.setNome(r.getNome());
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Regiao r) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            RegiaoJPA r1 = em.find(RegiaoJPA.class, r.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (r1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(r1);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
