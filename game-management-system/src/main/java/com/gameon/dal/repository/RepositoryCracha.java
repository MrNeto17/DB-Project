package com.gameon.dal.repository;


import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperCracha;
import com.gameon.dal.modelJPA.*;
import com.gameon.dal.utils.Utils;
import com.gameon.generic.IRepository;
import com.gameon.model.Cracha;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


public class RepositoryCracha implements IRepository<Cracha, Integer> {
    private final MapperCracha m = new MapperCracha();

    public List<Cracha> getAll() throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            List<CrachaJPA> l = em.createQuery("select c from CrachaJPA c", CrachaJPA.class)
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            List<Cracha> la = new ArrayList<>();
            for (CrachaJPA aj : l) {
                la.add(Utils.fromJPA(aj));
            }
            ds.validateWork();
            return la;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Cracha find(Integer Id) throws Exception {
        try {
            return m.read(Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void add(Cracha c) throws Exception {
        try {
            m.create(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void save(Cracha c) throws Exception {
        try {
            m.update(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Cracha c) throws Exception {
        try {
            m.delete(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void associarCracha(Integer jogadorId, String jogoReferencia, String nomeCracha) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();
            StoredProcedureQuery query = em.createStoredProcedureQuery("associarCracha")
                    .registerStoredProcedureParameter("p_jogador_id", Integer.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_jogo_referencia", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_nome_cracha", String.class, ParameterMode.IN)
                    .setParameter("p_jogador_id", jogadorId)
                    .setParameter("p_jogo_referencia", jogoReferencia)
                    .setParameter("p_nome_cracha", nomeCracha);

            query.execute();
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void associateBadge(int jogadorId, String jogoReferencia, String nomeCracha) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            JogadorJPA player = em.find(JogadorJPA.class, jogadorId);
            if (player == null) {
                throw new IllegalArgumentException("Jogador " + jogadorId + " não existe");
            }

            JogoJPA jogo = em.find(JogoJPA.class, jogoReferencia);
            if (jogo == null) {
                throw new IllegalArgumentException("Jogo " + jogoReferencia + " não existe");
            }

            CrachaJPA cracha = em.find(CrachaJPA.class, nomeCracha);
            if (cracha == null) {
                throw new IllegalArgumentException("Crachá " + nomeCracha + " não existe");
            }

            CrachaJPA crachaExiste = (CrachaJPA) em.createQuery("SELECT c.id, c.limitePontos " +
                            "FROM Cracha c " +
                            "WHERE c.jogo.referencia = :reference AND c.nome = :name")
                    .setParameter("reference", jogoReferencia)
                    .setParameter("name", nomeCracha)
                    .getSingleResult();
            if (crachaExiste == null) {
                throw new IllegalArgumentException("Crachá " + nomeCracha + " não encontrado para o jogo " + jogoReferencia);
            }

            Long normalPoints = em.createQuery("SELECT SUM(p.pontuacao) " +
                                    "FROM PartidaNormal p " +
                                    "JOIN p.partida pa " +
                                    "WHERE pa.jogo.referencia = :jogoReferencia AND p.jogador.id = :jogadorId",
                            Long.class)
                    .setParameter("jogoReferencia", jogoReferencia)
                    .setParameter("jogadorId", jogadorId)
                    .getSingleResult();

            Long multiPlayerPoints = em.createQuery("SELECT SUM(p.pontuacao) " +
                                    "FROM Participacao p " +
                                    "JOIN p.partidaMultiJogador pm " +
                                    "JOIN pm.partida pa " +
                                    "WHERE pa.jogo.referencia = :jogoReferencia AND p.jogador.id = :jogadorId",
                            Long.class)
                    .setParameter("jogoReferencia", jogoReferencia)
                    .setParameter("jogadorId", jogadorId)
                    .getSingleResult();

            int totalPoints = (normalPoints != null ? normalPoints.intValue() : 0)
                    + (multiPlayerPoints != null ? multiPlayerPoints.intValue() : 0);

            if (totalPoints >= cracha.getLimitePontos()) {
                CrachaJogadorPK crachaJogadorPK = new CrachaJogadorPK();
                crachaJogadorPK.setCracha(cracha.getId());
                crachaJogadorPK.setJogador(player.getId());
                CrachaJogadorJPA crachaJogador = em.find(CrachaJogadorJPA.class, crachaJogadorPK);

                if (crachaJogador == null) {
                    crachaJogador = new CrachaJogadorJPA();
                    crachaJogador.setCracha(cracha);
                    crachaJogador.setJogador(player);
                    em.persist(crachaJogador);
                }
            }

            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void aumentarPontosCrachaOptimistic(String crachaNome, String jogoRef) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            CrachaJPA cracha = em.createQuery(
                            "SELECT c FROM CrachaJPA c WHERE c.jogo.referencia = :jogoRef AND c.nome = :crachaNome",
                            CrachaJPA.class
                    )
                    .setParameter("jogoRef", jogoRef)
                    .setParameter("crachaNome", crachaNome)
                    .setLockMode(LockModeType.OPTIMISTIC)
                    .getSingleResult();

            cracha.setLimitePontos((int) (cracha.getLimitePontos() * 1.2));

            em.merge(cracha);
            ds.validateWork();
        } catch (OptimisticLockException ole) {
            System.out.println("Conflito : " + ole.getMessage());
            throw ole;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void aumentarPontosCrachaPessimist(String crachaNome, String jogoRef) throws Exception {
        try (DataScope ds = new DataScope()) {
            EntityManager em = ds.getEntityManager();

            CrachaJPA cracha = em.createQuery(
                            "SELECT c FROM CrachaJPA c WHERE c.jogo.referencia = :jogoRef AND c.nome = :crachaNome",
                            CrachaJPA.class
                    )
                    .setParameter("jogoRef", jogoRef)
                    .setParameter("crachaNome", crachaNome)
                    .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                    .getSingleResult();

            cracha.setLimitePontos((int) (cracha.getLimitePontos() * 1.2));

            em.merge(cracha);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
