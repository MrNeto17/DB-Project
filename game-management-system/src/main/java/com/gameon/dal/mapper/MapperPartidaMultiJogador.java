package com.gameon.dal.mapper;

import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.modelJPA.PartidaMultiJogadorJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IMapper;
import com.gameon.model.Partida;
import com.gameon.model.PartidaMultiJogador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class MapperPartidaMultiJogador implements IMapper<PartidaMultiJogador, Partida> {

    public Partida create(PartidaMultiJogador pm) throws Exception {
        try (DataScope ds = new DataScope()) {
            PartidaMultiJogadorJPA pmj = Utils.toJPA(pm);
            EntityManager em = ds.getEntityManager();
            em.persist(pmj);
            ds.validateWork();
            return pm.getPartida();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public PartidaMultiJogador read(Partida partida) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            PartidaMultiJogadorJPA pmj = em.find(PartidaMultiJogadorJPA.class, partida, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return Utils.fromJPA(pmj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(PartidaMultiJogador pm) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            PartidaMultiJogadorJPA pmj1 = em.find(PartidaMultiJogadorJPA.class, pm.getPartida(), LockModeType.PESSIMISTIC_WRITE);
            if (pmj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            pmj1.setEstado(PartidaMultiJogadorJPA.Estado.valueOf(pm.getEstado().name()));
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(PartidaMultiJogador pm) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            PartidaMultiJogadorJPA pmj = em.find(PartidaMultiJogadorJPA.class, pm.getPartida(), LockModeType.PESSIMISTIC_WRITE);
            if (pmj == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(pmj);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
