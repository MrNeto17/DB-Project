package com.gameon.dal.repository;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperCrachaJogador;
import com.gameon.dal.modelJPA.CrachaJogadorJPA;
import com.gameon.dal.modelJPA.CrachaJogadorPK;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IRepository;
import com.gameon.model.CrachaJogador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.util.ArrayList;
import java.util.List;


public class RepositoryCrachaJogador implements IRepository<CrachaJogador, CrachaJogadorPK> {
    private final MapperCrachaJogador m = new MapperCrachaJogador();

    public List<CrachaJogador> getAll() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<CrachaJogadorJPA> l = em.createQuery("select c from CrachaJogadorJPA c", CrachaJogadorJPA.class)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            List<CrachaJogador> la = new ArrayList<>();
            for (CrachaJogadorJPA aj : l) {
                la.add(Utils.fromJPA(aj));
            }
            ds.validateWork();
            return la;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public CrachaJogador find(CrachaJogadorPK Id) throws Exception {
        try {
            return m.read(Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void add(CrachaJogador cj) throws Exception {
        try {
            m.create(cj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void save(CrachaJogador cj) throws Exception {
        try {
            m.update(cj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(CrachaJogador cj) throws Exception {
        try {
            m.delete(cj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
