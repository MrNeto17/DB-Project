package com.gameon.dal.mapper;

import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.modelJPA.ParticipacaoConversaJPA;
import com.gameon.dal.modelJPA.ParticipacaoConversaPK;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IMapper;
import com.gameon.model.ParticipacaoConversa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class MapperParticipacaoConversa implements IMapper<ParticipacaoConversa, ParticipacaoConversaPK> {

    public ParticipacaoConversaPK create(ParticipacaoConversa pc) throws Exception {
        try (DataScope ds = new DataScope()) {
            ParticipacaoConversaJPA pcj = Utils.toJPA(pc);
            EntityManager em = ds.getEntityManager();
            em.persist(pcj);
            ds.validateWork();
            return pc.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public ParticipacaoConversa read(ParticipacaoConversaPK id) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            ParticipacaoConversaJPA pcj = em.find(ParticipacaoConversaJPA.class, id, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return Utils.fromJPA(pcj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(ParticipacaoConversa pc) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            ParticipacaoConversaJPA pj1 = em.find(ParticipacaoConversaJPA.class, pc.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (pj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(ParticipacaoConversa pc) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            ParticipacaoConversaJPA pcj = em.find(ParticipacaoConversaJPA.class, pc.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (pcj == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(pcj);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
