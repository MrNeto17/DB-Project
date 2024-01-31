package com.gameon.dal.repository;

import com.gameon.dal.datascope.DataScope;
import com.gameon.model.JogadorTotalInfo;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RepositoryJogadorTotalInfo {
    public List<JogadorTotalInfo> getJogadorTotalInfo() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<JogadorTotalInfo> list = em.createQuery("SELECT j FROM JogadorTotalInfo j", JogadorTotalInfo.class)
                    .getResultList();
            ds.validateWork();
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
