package com.gameon.dal.repository;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperConversa;
import com.gameon.dal.modelJPA.ConversaJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IRepository;
import com.gameon.model.Conversa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

import java.util.ArrayList;
import java.util.List;


public class RepositoryConversa implements IRepository<Conversa, Integer> {
    private final MapperConversa m = new MapperConversa();

    public List<Conversa> getAll() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<ConversaJPA> l = em.createQuery("select c from ConversaJPA c", ConversaJPA.class)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            List<Conversa> la = new ArrayList<>();
            for (ConversaJPA aj : l) {
                la.add(Utils.fromJPA(aj));
            }
            ds.validateWork();
            return la;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Conversa find(Integer Id) throws Exception {
        try {
            return m.read(Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void add(Conversa c) throws Exception {
        try {
            m.create(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void save(Conversa c) throws Exception {
        try {
            m.update(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Conversa c) throws Exception {
        try {
            m.delete(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Integer iniciarConversa(Integer jogadorId, String nomeConversa) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            StoredProcedureQuery query = em.createStoredProcedureQuery("iniciarConversa")
                    .registerStoredProcedureParameter("p_jogador_id", Integer.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_nome_conversa", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_conversa_id", Integer.class, ParameterMode.OUT)
                    .setParameter("p_jogador_id", jogadorId)
                    .setParameter("p_nome_conversa", nomeConversa);

            query.execute();
            Integer conversaId = (Integer) query.getOutputParameterValue("p_conversa_id");
            ds.validateWork();
            return conversaId;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void juntarConversa(Integer jogadorId, Integer conversaId) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            StoredProcedureQuery query = em.createStoredProcedureQuery("juntarConversa")
                    .registerStoredProcedureParameter("p_jogador_id", Integer.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_conversa_id", Integer.class, ParameterMode.IN)
                    .setParameter("p_jogador_id", jogadorId)
                    .setParameter("p_conversa_id", conversaId);

            query.execute();
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void enviarMensagem(Integer jogadorId, Integer conversaId, String texto) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            StoredProcedureQuery query = em.createStoredProcedureQuery("enviarMensagem")
                    .registerStoredProcedureParameter("p_jogador_id", Integer.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_conversa_id", Integer.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_texto", String.class, ParameterMode.IN)
                    .setParameter("p_jogador_id", jogadorId)
                    .setParameter("p_conversa_id", conversaId)
                    .setParameter("p_texto", texto);

            query.execute();
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
