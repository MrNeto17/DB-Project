package com.gameon.dal.repository;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperMensagem;
import com.gameon.dal.modelJPA.MensagemJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IRepository;
import com.gameon.model.Mensagem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.util.ArrayList;
import java.util.List;


public class RepositoryMensagem implements IRepository<Mensagem, Integer> {
    private final MapperMensagem m = new MapperMensagem();

    public List<Mensagem> getAll() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<MensagemJPA> l = em.createQuery("select m from MensagemJPA m", MensagemJPA.class)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            List<Mensagem> la = new ArrayList<>();
            for (MensagemJPA aj : l) {
                la.add(Utils.fromJPA(aj));
            }
            ds.validateWork();
            return la;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Mensagem find(Integer Id) throws Exception {
        try {
            return m.read(Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void add(Mensagem m) throws Exception {
        try {
            this.m.create(m);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void save(Mensagem m) throws Exception {
        try {
            this.m.update(m);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Mensagem m) throws Exception {
        try {
            this.m.delete(m);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
