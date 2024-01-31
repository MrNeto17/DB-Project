package com.gameon.dal.repository;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperAmizade;
import com.gameon.dal.modelJPA.AmizadeJPA;
import com.gameon.dal.modelJPA.AmizadePK;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IRepository;
import com.gameon.model.Amizade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.util.ArrayList;
import java.util.List;


public class RepositoryAmizade implements IRepository<Amizade, AmizadePK> {
    private final MapperAmizade m = new MapperAmizade();

    public List<Amizade> getAll() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<AmizadeJPA> l = em.createQuery("SELECT a from AmizadeJPA a", AmizadeJPA.class)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            List<Amizade> la = new ArrayList<>();
            for (AmizadeJPA aj : l) {
                la.add(Utils.fromJPA(aj));
            }
            ds.validateWork();
            return la;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Amizade find(AmizadePK Id) throws Exception {
        try {
            return m.read(Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void add(Amizade a) throws Exception {
        try {
            m.create(a);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void save(Amizade a) throws Exception {
        try {
            m.update(a);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Amizade a) throws Exception {
        try {
            m.delete(a);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
