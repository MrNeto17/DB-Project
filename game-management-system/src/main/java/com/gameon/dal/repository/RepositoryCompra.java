package com.gameon.dal.repository;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperCompra;
import com.gameon.dal.modelJPA.CompraJPA;
import com.gameon.dal.modelJPA.CompraPK;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IRepository;
import com.gameon.model.Compra;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.util.ArrayList;
import java.util.List;


public class RepositoryCompra implements IRepository<Compra, CompraPK> {
    private final MapperCompra m = new MapperCompra();

    public List<Compra> getAll() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<CompraJPA> l = em.createQuery("select c from CompraJPA c", CompraJPA.class)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            List<Compra> la = new ArrayList<>();
            for (CompraJPA aj : l) {
                la.add(Utils.fromJPA(aj));
            }
            ds.validateWork();
            return la;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Compra find(CompraPK Id) throws Exception {
        try {
            return m.read(Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void add(Compra c) throws Exception {
        try {
            m.create(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void save(Compra c) throws Exception {
        try {
            m.update(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Compra c) throws Exception {
        try {
            m.delete(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
