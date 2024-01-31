package com.gameon.dal.mapper;

import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.modelJPA.EstatisticasJogadorJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IMapper;
import com.gameon.model.EstatisticasJogador;
import com.gameon.model.Jogador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class MapperEstatisticasJogador implements IMapper<EstatisticasJogador, Jogador> {

    public Jogador create(EstatisticasJogador ej) throws Exception {
        try (DataScope ds = new DataScope()) {
            EstatisticasJogadorJPA ejj = Utils.toJPA(ej);
            EntityManager em = ds.getEntityManager();
            em.persist(ejj);
            ds.validateWork();
            return ej.getJogador();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public EstatisticasJogador read(Jogador jogador) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            EstatisticasJogadorJPA ejj = em.find(EstatisticasJogadorJPA.class, jogador, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return Utils.fromJPA(ejj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(EstatisticasJogador ej) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            EstatisticasJogadorJPA ejj1 = em.find(EstatisticasJogadorJPA.class, ej.getJogador(), LockModeType.PESSIMISTIC_WRITE);
            if (ejj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            ejj1.setNumerosPartidas(ej.getNumerosPartidas());
            ejj1.setNumerosJogos(ej.getNumerosJogos());
            ejj1.setTotalPontos(ej.getTotalPontos());
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(EstatisticasJogador ej) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            EstatisticasJogadorJPA ejj = em.find(EstatisticasJogadorJPA.class, ej.getJogador(), LockModeType.PESSIMISTIC_WRITE);
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
