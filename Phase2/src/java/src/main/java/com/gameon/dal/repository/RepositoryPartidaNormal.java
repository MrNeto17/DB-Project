package com.gameon.dal.repository;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperPartidaNormal;
import com.gameon.dal.modelJPA.PartidaNormalJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IRepository;
import com.gameon.model.Partida;
import com.gameon.model.PartidaNormal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.util.ArrayList;
import java.util.List;


public class RepositoryPartidaNormal implements IRepository<PartidaNormal, Partida> {
    private final MapperPartidaNormal m = new MapperPartidaNormal();

    public List<PartidaNormal> getAll() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<PartidaNormalJPA> l = em.createQuery("select p from PartidaNormalJPA p", PartidaNormalJPA.class)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            List<PartidaNormal> la = new ArrayList<>();
            for (PartidaNormalJPA aj : l) {
                la.add(Utils.fromJPA(aj));
            }
            ds.validateWork();
            return la;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public PartidaNormal find(Partida partida) throws Exception {
        try {
            return m.read(partida);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void add(PartidaNormal pn) throws Exception {
        try {
            m.create(pn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void save(PartidaNormal pn) throws Exception {
        try {
            m.update(pn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(PartidaNormal pn) throws Exception {
        try {
            m.delete(pn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
