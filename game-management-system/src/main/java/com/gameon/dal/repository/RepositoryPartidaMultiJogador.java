package com.gameon.dal.repository;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperPartidaMultiJogador;
import com.gameon.dal.modelJPA.PartidaMultiJogadorJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IRepository;
import com.gameon.model.Partida;
import com.gameon.model.PartidaMultiJogador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.util.ArrayList;
import java.util.List;


public class RepositoryPartidaMultiJogador implements IRepository<PartidaMultiJogador, Partida> {
    private final MapperPartidaMultiJogador m = new MapperPartidaMultiJogador();

    public List<PartidaMultiJogador> getAll() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<PartidaMultiJogadorJPA> l = em.createQuery("select p from PartidaMultiJogadorJPA p", PartidaMultiJogadorJPA.class)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            List<PartidaMultiJogador> la = new ArrayList<>();
            for (PartidaMultiJogadorJPA aj : l) {
                la.add(Utils.fromJPA(aj));
            }
            ds.validateWork();
            return la;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public PartidaMultiJogador find(Partida partida) throws Exception {
        try {
            return m.read(partida);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void add(PartidaMultiJogador pmj) throws Exception {
        try {
            m.create(pmj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void save(PartidaMultiJogador pmj) throws Exception {
        try {
            m.update(pmj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(PartidaMultiJogador pmj) throws Exception {
        try {
            m.delete(pmj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
