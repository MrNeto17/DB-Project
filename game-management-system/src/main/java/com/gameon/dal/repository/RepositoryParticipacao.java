package com.gameon.dal.repository;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperParticipacao;
import com.gameon.dal.modelJPA.ParticipacaoJPA;
import com.gameon.dal.modelJPA.ParticipacaoPK;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IRepository;
import com.gameon.model.Participacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.util.ArrayList;
import java.util.List;


public class RepositoryParticipacao implements IRepository<Participacao, ParticipacaoPK> {
    private final MapperParticipacao m = new MapperParticipacao();

    public List<Participacao> getAll() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<ParticipacaoJPA> l = em.createQuery("select p from ParticipacaoJPA p", ParticipacaoJPA.class)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            List<Participacao> la = new ArrayList<>();
            for (ParticipacaoJPA aj : l) {
                la.add(Utils.fromJPA(aj));
            }
            ds.validateWork();
            return la;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Participacao find(ParticipacaoPK Id) throws Exception {
        try {
            return m.read(Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void add(Participacao p) throws Exception {
        try {
            m.create(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void save(Participacao p) throws Exception {
        try {
            m.update(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Participacao p) throws Exception {
        try {
            m.delete(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
