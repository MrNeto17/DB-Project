package com.gameon.dal.mapper;

import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.modelJPA.MensagemJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IMapper;
import com.gameon.model.Mensagem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class MapperMensagem implements IMapper<Mensagem, Integer> {

    public Integer create(Mensagem m) throws Exception {
        try (DataScope ds = new DataScope()) {
            MensagemJPA mj = Utils.toJPA(m);
            EntityManager em = ds.getEntityManager();
            em.persist(mj);
            ds.validateWork();
            return m.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Mensagem read(Integer id) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            MensagemJPA mj = em.find(MensagemJPA.class, id, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return Utils.fromJPA(mj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(Mensagem m) throws Exception {
        try (DataScope ds = new DataScope()) {
            MensagemJPA mj = Utils.toJPA(m);
            EntityManager em = ds.getEntityManager();
            em.flush();
            MensagemJPA mj1 = em.find(MensagemJPA.class, m.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (mj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            mj1.setConversa(mj.getConversa());
            mj1.setOrdem(m.getOrdem());
            mj1.setJogador(mj.getJogador());
            mj1.setDataHora(m.getDataHora());
            mj1.setTexto(m.getTexto());
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Mensagem m) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            MensagemJPA mj = em.find(MensagemJPA.class, m.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (mj == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(mj);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
