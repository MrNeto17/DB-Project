package com.gameon.dal.mapper;

import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.modelJPA.EstatisticasJogoJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IMapper;
import com.gameon.model.EstatisticasJogo;
import com.gameon.model.Jogo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class MapperEstatisticasJogo implements IMapper<EstatisticasJogo, Jogo> {

    public Jogo create(EstatisticasJogo ej) throws Exception {
        try (DataScope ds = new DataScope()) {
            EstatisticasJogoJPA ejj = Utils.toJPA(ej);
            EntityManager em = ds.getEntityManager();
            em.persist(ejj);
            ds.validateWork();
            return ej.getJogo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public EstatisticasJogo read(Jogo jogo) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            EstatisticasJogoJPA ejj = em.find(EstatisticasJogoJPA.class, jogo, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return Utils.fromJPA(ejj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(EstatisticasJogo ej) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            EstatisticasJogoJPA ejj1 = em.find(EstatisticasJogoJPA.class, ej.getJogo(), LockModeType.PESSIMISTIC_WRITE);
            if (ejj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            ejj1.setNumerosPartidas(ej.getNumerosPartidas());
            ejj1.setNumerosJogadores(ej.getNumerosJogadores());
            ejj1.setTotalPontos(ej.getTotalPontos());
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(EstatisticasJogo ej) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            EstatisticasJogoJPA ejj = em.find(EstatisticasJogoJPA.class, ej.getJogo(), LockModeType.PESSIMISTIC_WRITE);
            if (ejj == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(ejj);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
