package com.gameon.dal.repository;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperRegiao;
import com.gameon.dal.modelJPA.RegiaoJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IRepository;
import com.gameon.model.Regiao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.util.ArrayList;
import java.util.List;


public class RepositoryRegiao implements IRepository<Regiao, Integer> {

    public List<Regiao> getAll() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<RegiaoJPA> l = em.createQuery("select r from RegiaoJPA r", RegiaoJPA.class)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            List<Regiao> la = new ArrayList<>();
            for (RegiaoJPA aj : l) {
                la.add(Utils.fromJPA(aj));
            }
            ds.validateWork();
            return la;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Regiao find(Integer Id) throws Exception {
        MapperRegiao m = new MapperRegiao();
        try {
            return m.read(Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void add(Regiao r) throws Exception {
        MapperRegiao m = new MapperRegiao();
        try {
            m.create(r);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void save(Regiao r) throws Exception {
        MapperRegiao m = new MapperRegiao();
        try {
            m.update(r);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Regiao r) throws Exception {
        MapperRegiao m = new MapperRegiao();
        try {
            m.delete(r);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
