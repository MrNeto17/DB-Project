package com.gameon.dal.mapper;

import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.modelJPA.PartidaNormalJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IMapper;
import com.gameon.model.Partida;
import com.gameon.model.PartidaNormal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class MapperPartidaNormal implements IMapper<PartidaNormal, Partida> {

    public Partida create(PartidaNormal pn) throws Exception {
        try (DataScope ds = new DataScope()) {
            PartidaNormalJPA pnj = Utils.toJPA(pn);
            EntityManager em = ds.getEntityManager();
            em.persist(pnj);
            ds.validateWork();
            return pn.getPartida();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public PartidaNormal read(Partida partida) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            PartidaNormalJPA pnj = em.find(PartidaNormalJPA.class, partida, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return Utils.fromJPA(pnj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(PartidaNormal pn) throws Exception {
        try (DataScope ds = new DataScope()) {
            PartidaNormalJPA pnj = Utils.toJPA(pn);
            EntityManager em = ds.getEntityManager();
            em.flush();
            PartidaNormalJPA pnj1 = em.find(PartidaNormalJPA.class, pn.getPartida(), LockModeType.PESSIMISTIC_WRITE);
            if (pnj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            pnj1.setJogador(pnj.getJogador());
            pnj1.setDificuldade(pn.getDificuldade());
            pnj1.setPontuacao(pn.getPontuacao());
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(PartidaNormal pn) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            PartidaNormalJPA pnj = em.find(PartidaNormalJPA.class, pn.getPartida(), LockModeType.PESSIMISTIC_WRITE);
            if (pnj == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(pnj);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
