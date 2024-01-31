package com.gameon.dal.mapper;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.modelJPA.AmizadeJPA;
import com.gameon.dal.modelJPA.AmizadePK;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IMapper;
import com.gameon.model.Amizade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class MapperAmizade implements IMapper<Amizade, AmizadePK> {

    public AmizadePK create(Amizade a) throws Exception {
        try (DataScope ds = new DataScope()) {
            AmizadeJPA aj = Utils.toJPA(a);
            EntityManager em = ds.getEntityManager();
            em.persist(aj);
            ds.validateWork();
            return a.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Amizade read(AmizadePK id) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            AmizadeJPA aj = em.find(AmizadeJPA.class, id, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return Utils.fromJPA(aj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(Amizade a) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            AmizadeJPA aj1 = em.find(AmizadeJPA.class, a.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (aj1 == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            //
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Amizade a) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();
            AmizadeJPA aj = em.find(AmizadeJPA.class, a.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (aj == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(aj);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}

