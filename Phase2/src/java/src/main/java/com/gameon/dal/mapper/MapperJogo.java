package com.gameon.dal.mapper;

import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.modelJPA.JogoJPA;
import com.gameon.dal.modelJPA.RegiaoJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IMapper;
import com.gameon.model.Jogo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class MapperJogo implements IMapper<Jogo, String> {

    public String create(Jogo j) throws Exception {
        try (DataScope ds = new DataScope()) {
            JogoJPA jj = Utils.toJPA(j);
            EntityManager em = ds.getEntityManager();
            em.persist(jj);
            ds.validateWork();
            return j.getReferencia();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Jogo read(String referencia) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            JogoJPA rj = em.find(JogoJPA.class, referencia, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return Utils.fromJPA(rj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(Jogo j) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            JogoJPA jj1 = em.find(JogoJPA.class, j.getReferencia(), LockModeType.PESSIMISTIC_WRITE);
            if (jj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            jj1.setNome(j.getNome());
            jj1.setUrl(j.getUrl());
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Jogo j) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            RegiaoJPA jj = em.find(RegiaoJPA.class, j.getReferencia(), LockModeType.PESSIMISTIC_WRITE);
            if (jj == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(jj);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
