package com.gameon.dal.mapper;

import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.modelJPA.ParticipacaoJPA;
import com.gameon.dal.modelJPA.ParticipacaoPK;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IMapper;
import com.gameon.model.Participacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class MapperParticipacao implements IMapper<Participacao, ParticipacaoPK> {

    public ParticipacaoPK create(Participacao p) throws Exception {
        try (DataScope ds = new DataScope()) {
            ParticipacaoJPA pj = Utils.toJPA(p);
            EntityManager em = ds.getEntityManager();
            em.persist(pj);
            ds.validateWork();
            return p.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Participacao read(ParticipacaoPK id) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            ParticipacaoJPA pj = em.find(ParticipacaoJPA.class, id, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return Utils.fromJPA(pj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(Participacao p) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            ParticipacaoJPA pj1 = em.find(ParticipacaoJPA.class, p.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (pj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Participacao p) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            ParticipacaoJPA pj = em.find(ParticipacaoJPA.class, p.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (pj == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(pj);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
