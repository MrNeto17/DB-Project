package com.gameon.dal.repository;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperEstatisticasJogador;
import com.gameon.dal.modelJPA.EstatisticasJogadorJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IRepository;
import com.gameon.model.EstatisticasJogador;
import com.gameon.model.Jogador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.util.ArrayList;
import java.util.List;


public class RepositoryEstatisticasJogador implements IRepository<EstatisticasJogador, Jogador> {
    private final MapperEstatisticasJogador m = new MapperEstatisticasJogador();

    public List<EstatisticasJogador> getAll() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<EstatisticasJogadorJPA> l = em.createQuery("select e from EstatisticasJogadorJPA e", EstatisticasJogadorJPA.class)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            List<EstatisticasJogador> la = new ArrayList<>();
            for (EstatisticasJogadorJPA aj : l) {
                la.add(Utils.fromJPA(aj));
            }
            ds.validateWork();
            return la;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public EstatisticasJogador find(Jogador Id) throws Exception {
        try {
            return m.read(Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void add(EstatisticasJogador ej) throws Exception {
        try {
            m.create(ej);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void save(EstatisticasJogador ej) throws Exception {
        try {
            m.update(ej);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(EstatisticasJogador ej) throws Exception {
        try {
            m.delete(ej);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
