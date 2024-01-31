package com.gameon.dal.repository;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperParticipacaoConversa;
import com.gameon.dal.modelJPA.ParticipacaoConversaJPA;
import com.gameon.dal.modelJPA.ParticipacaoConversaPK;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IRepository;
import com.gameon.model.ParticipacaoConversa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.util.ArrayList;
import java.util.List;


public class RepositoryParticipacaoConversa implements IRepository<ParticipacaoConversa, ParticipacaoConversaPK> {
    private final MapperParticipacaoConversa m = new MapperParticipacaoConversa();

    public List<ParticipacaoConversa> getAll() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<ParticipacaoConversaJPA> l = em.createQuery("select p from ParticipacaoConversaJPA p", ParticipacaoConversaJPA.class)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            List<ParticipacaoConversa> la = new ArrayList<>();
            for (ParticipacaoConversaJPA aj : l) {
                la.add(Utils.fromJPA(aj));
            }
            ds.validateWork();
            return la;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public ParticipacaoConversa find(ParticipacaoConversaPK Id) throws Exception {
        try {
            return m.read(Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void add(ParticipacaoConversa pc) throws Exception {
        try {
            m.create(pc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void save(ParticipacaoConversa pc) throws Exception {
        try {
            m.update(pc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(ParticipacaoConversa pc) throws Exception {
        try {
            m.delete(pc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
