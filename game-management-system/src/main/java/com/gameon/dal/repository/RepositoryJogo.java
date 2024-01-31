package com.gameon.dal.repository;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperJogo;
import com.gameon.dal.modelJPA.JogoJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IRepository;
import com.gameon.model.Jogo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.util.ArrayList;
import java.util.List;


public class RepositoryJogo implements IRepository<Jogo, String> {
    private final MapperJogo m = new MapperJogo();

    public List<Jogo> getAll() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<JogoJPA> l = em.createQuery("select j from JogoJPA j", JogoJPA.class)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            List<Jogo> la = new ArrayList<>();
            for (JogoJPA aj : l) {
                la.add(Utils.fromJPA(aj));
            }
            ds.validateWork();
            return la;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Jogo find(String referencia) throws Exception {
        try {
            return m.read(referencia);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void add(Jogo j) throws Exception {
        try {
            m.create(j);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void save(Jogo j) throws Exception {
        try {
            m.update(j);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Jogo j) throws Exception {
        try {
            m.delete(j);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List pontosJogoPorJogador(String jogoReferencia) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List results = em.createNativeQuery("SELECT * FROM PontosJogoPorJogador(:jogoReferencia)")
                    .setParameter("jogoReferencia", jogoReferencia)
                    .getResultList();
            ds.validateWork();
            return results;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
