package com.gameon.dal.mapper;

import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.modelJPA.PartidaJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IMapper;
import com.gameon.model.Partida;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class MapperPartida implements IMapper<Partida, Integer> {

    public Integer create(Partida p) throws Exception {
        try (DataScope ds = new DataScope()) {
            PartidaJPA pj = Utils.toJPA(p);
            EntityManager em = ds.getEntityManager();
            em.persist(pj);
            ds.validateWork();
            return p.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Partida read(Integer id) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            PartidaJPA rj = em.find(PartidaJPA.class, id, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return Utils.fromJPA(rj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(Partida p) throws Exception {
        try (DataScope ds = new DataScope()) {
            PartidaJPA pj = Utils.toJPA(p);
            EntityManager em = ds.getEntityManager();
            em.flush();
            PartidaJPA pj1 = em.find(PartidaJPA.class, p.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (pj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            pj1.setJogo(pj.getJogo());
            pj1.setDataInicio(p.getDataInicio());
            pj1.setDataFim(p.getDataFim());
            pj1.setRegiao(pj.getRegiao());
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Partida p) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            PartidaJPA pj = em.find(PartidaJPA.class, p.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (pj == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(pj);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
