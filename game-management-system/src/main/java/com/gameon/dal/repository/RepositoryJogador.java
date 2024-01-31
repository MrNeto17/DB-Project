package com.gameon.dal.repository;

import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperJogador;
import com.gameon.dal.modelJPA.JogadorJPA;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IRepository;
import com.gameon.model.Jogador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import java.util.ArrayList;
import java.util.List;

public class RepositoryJogador implements IRepository<Jogador, Integer> {
    private final MapperJogador m = new MapperJogador();

    public List<Jogador> getAll() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<JogadorJPA> l = em.createQuery("select j from JogadorJPA j", JogadorJPA.class)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            List<Jogador> la = new ArrayList<>();
            for (JogadorJPA aj : l) {
                la.add(Utils.fromJPA(aj));
            }
            ds.validateWork();
            return la;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Jogador find(Integer Id) throws Exception {
        try {
            return m.read(Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void add(Jogador j) throws Exception {
        try {
            m.create(j);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void save(Jogador j) throws Exception {
        try {
            m.update(j);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Jogador j) throws Exception {
        try {
            m.delete(j);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void criarJogador(String email, String username, int regiaoId) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.createNativeQuery("SELECT criarJogador(:email, :username, :regiaoId)")
                    .setParameter("email", email)
                    .setParameter("username", username)
                    .setParameter("regiaoId", regiaoId)
                    .getSingleResult();
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void desativarJogador(int jogadorId) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.createNativeQuery("SELECT desativarJogador(:jogadorId)")
                    .setParameter("jogadorId", jogadorId)
                    .getSingleResult();
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void banirJogador(int jogadorId) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            em.createNativeQuery("SELECT banirJogador(:jogadorId)")
                    .setParameter("jogadorId", jogadorId)
                    .getSingleResult();
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int totalPontosJogador(int jogadorId) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            int totalPoints = (int) em.createNativeQuery("SELECT totalPontosJogador(:jogadorId)")
                    .setParameter("jogadorId", jogadorId)
                    .getSingleResult();
            ds.validateWork();
            return totalPoints;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public int totalJogosJogador(int jogadorId) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            int totalGames = (int) em.createNativeQuery("SELECT totalJogosJogador(:jogadorId)")
                    .setParameter("jogadorId", jogadorId)
                    .getSingleResult();
            ds.validateWork();
            return totalGames;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
