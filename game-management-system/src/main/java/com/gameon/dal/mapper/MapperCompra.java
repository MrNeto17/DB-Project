package com.gameon.dal.mapper;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.modelJPA.CompraJPA;
import com.gameon.dal.modelJPA.CompraPK;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IMapper;
import com.gameon.model.Compra;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class MapperCompra implements IMapper<Compra, CompraPK> {

    public CompraPK create(Compra c) throws Exception {
        try (DataScope ds = new DataScope()) {
            CompraJPA cj = Utils.toJPA(c);
            EntityManager em = ds.getEntityManager();
            em.persist(cj);
            ds.validateWork();
            return c.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Compra read(CompraPK id) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            CompraJPA cj = em.find(CompraJPA.class, id, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return Utils.fromJPA(cj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(Compra c) throws Exception {
        try (DataScope ds = new DataScope()) {
            CompraJPA cj = Utils.toJPA(c);
            EntityManager em = ds.getEntityManager();
            em.flush();
            CompraJPA cj1 = em.find(CompraJPA.class, c.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (cj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            //
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Compra c) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            CompraJPA cj = em.find(CompraJPA.class, c.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (cj == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(cj);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
