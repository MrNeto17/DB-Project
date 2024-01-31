package com.gameon.dal.repository;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperEstatisticasJogo;
import com.gameon.dal.modelJPA.EstatisticasJogoJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IRepository;
import com.gameon.model.EstatisticasJogo;
import com.gameon.model.Jogo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.util.ArrayList;
import java.util.List;


public class RepositoryEstatisticasJogo implements IRepository<EstatisticasJogo, Jogo> {
    private final MapperEstatisticasJogo m = new MapperEstatisticasJogo();

    public List<EstatisticasJogo> getAll() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<EstatisticasJogoJPA> l = em.createQuery("select e from EstatisticasJogoJPA e", EstatisticasJogoJPA.class)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            List<EstatisticasJogo> la = new ArrayList<>();
            for (EstatisticasJogoJPA aj : l) {
                la.add(Utils.fromJPA(aj));
            }
            ds.validateWork();
            return la;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public EstatisticasJogo find(Jogo Id) throws Exception {
        try {
            return m.read(Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void add(EstatisticasJogo ej) throws Exception {
        try {
            m.create(ej);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void save(EstatisticasJogo ej) throws Exception {
        try {
            m.update(ej);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(EstatisticasJogo ej) throws Exception {
        try {
            m.delete(ej);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
