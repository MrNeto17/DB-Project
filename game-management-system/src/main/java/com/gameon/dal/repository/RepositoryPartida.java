package com.gameon.dal.repository;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperPartida;
import com.gameon.dal.modelJPA.PartidaJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IRepository;
import com.gameon.model.Partida;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.util.ArrayList;
import java.util.List;


public class RepositoryPartida implements IRepository<Partida, Integer> {
    private final MapperPartida m = new MapperPartida();

    public List<Partida> getAll() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<PartidaJPA> l = em.createQuery("select p from PartidaJPA p", PartidaJPA.class)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            List<Partida> la = new ArrayList<>();
            for (PartidaJPA aj : l) {
                la.add(Utils.fromJPA(aj));
            }
            ds.validateWork();
            return la;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Partida find(Integer Id) throws Exception {
        try {
            return m.read(Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void add(Partida p) throws Exception {
        try {
            m.create(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void save(Partida p) throws Exception {
        try {
            m.update(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Partida p) throws Exception {
        try {
            m.delete(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
