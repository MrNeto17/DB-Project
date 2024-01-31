package com.gameon.dal.mapper;

import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.modelJPA.ConversaJPA;
import com.gameon.dal.modelJPA.CrachaJogadorJPA;
import com.gameon.dal.modelJPA.CrachaJogadorPK;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IMapper;
import com.gameon.model.CrachaJogador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class MapperCrachaJogador implements IMapper<CrachaJogador, CrachaJogadorPK> {

    public CrachaJogadorPK create(CrachaJogador cj) throws Exception {
        try (DataScope ds = new DataScope()) {
            CrachaJogadorJPA cjj = Utils.toJPA(cj);
            EntityManager em = ds.getEntityManager();
            em.persist(cjj);
            ds.validateWork();
            return cj.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public CrachaJogador read(CrachaJogadorPK id) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            CrachaJogadorJPA cj = em.find(CrachaJogadorJPA.class, id, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return Utils.fromJPA(cj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(CrachaJogador cj) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            ConversaJPA cj1 = em.find(ConversaJPA.class, cj.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (cj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            //
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(CrachaJogador cj) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            CrachaJogadorJPA cj1 = em.find(CrachaJogadorJPA.class, cj.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (cj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(cj);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
