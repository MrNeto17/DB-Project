package com.gameon.dal.utils;

import com.gameon.dal.modelJPA.*;
import com.gameon.model.*;


public class Utils {
    public static RegiaoJPA toJPA(Regiao r) {
        RegiaoJPA rj = new RegiaoJPA();
        rj.setId(r.getId());
        rj.setNome(r.getNome());
        return rj;
    }

    public static Regiao fromJPA(RegiaoJPA rj) {
        Regiao r = new Regiao();
        r.setId(rj.getId());
        r.setNome(rj.getNome());
        return r;
    }

    public static JogadorJPA toJPA(Jogador j) {
        JogadorJPA jj = new JogadorJPA();
        jj.setId(j.getId());
        jj.setEmail(j.getEmail());
        jj.setUsername(j.getUsername());
        jj.setEstado(JogadorJPA.Estado.valueOf(j.getEstado().name()));
        jj.setRegiao(toJPA(j.getRegiao()));
        return jj;
    }

    public static Jogador fromJPA(JogadorJPA jj) {
        Jogador j = new Jogador();
        j.setId(jj.getId());
        j.setEmail(jj.getEmail());
        j.setUsername(jj.getUsername());
        j.setEstado(Jogador.Estado.valueOf(jj.getEstado().name()));
        j.setRegiao(fromJPA(jj.getRegiao()));
        return j;
    }

    public static JogoJPA toJPA(Jogo j) {
        JogoJPA jj = new JogoJPA();
        jj.setReferencia(j.getReferencia());
        jj.setNome(j.getNome());
        jj.setUrl(j.getUrl());
        return jj;
    }

    public static Jogo fromJPA(JogoJPA jj) {
        Jogo j = new Jogo();
        j.setReferencia(jj.getReferencia());
        j.setNome(jj.getNome());
        j.setUrl(jj.getUrl());
        return j;
    }

    public static CompraJPA toJPA(Compra c) {
        CompraJPA cj = new CompraJPA();
        CompraPK pk = new CompraPK();
        pk.setJogador(c.getJogador().getId());
        pk.setJogo(c.getJogo().getReferencia());
        cj.setId(pk);
        cj.setJogador(toJPA(c.getJogador()));
        cj.setJogo(toJPA(c.getJogo()));
        cj.setDataCompra(c.getDataCompra());
        cj.setPreco(c.getPreco());
        return cj;
    }

    public static Compra fromJPA(CompraJPA cj) {
        Compra c = new Compra();
        c.setJogador(fromJPA(cj.getJogador()));
        c.setJogo(fromJPA(cj.getJogo()));
        c.setDataCompra(cj.getDataCompra());
        c.setPreco(cj.getPreco());
        return c;
    }

    public static PartidaJPA toJPA(Partida p) {
        PartidaJPA pj = new PartidaJPA();
        pj.setId(p.getId());
        pj.setJogo(toJPA(p.getJogo()));
        pj.setDataInicio(p.getDataInicio());
        pj.setDataFim(p.getDataFim());
        pj.setRegiao(toJPA(p.getRegiao()));
        return pj;
    }

    public static Partida fromJPA(PartidaJPA pj) {
        Partida p = new Partida();
        p.setId(pj.getId());
        p.setJogo(fromJPA(pj.getJogo()));
        p.setDataInicio(pj.getDataInicio());
        p.setDataFim(pj.getDataFim());
        p.setRegiao(fromJPA(pj.getRegiao()));
        return p;
    }

    public static PartidaNormalJPA toJPA(PartidaNormal p) {
        PartidaNormalJPA pj = new PartidaNormalJPA();
        pj.setId(p.getId());
        pj.setPartida(toJPA(p.getPartida()));
        pj.setJogador(toJPA(p.getJogador()));
        pj.setDificuldade(p.getDificuldade());
        pj.setPontuacao(p.getPontuacao());
        return pj;
    }

    public static PartidaNormal fromJPA(PartidaNormalJPA pj) {
        PartidaNormal p = new PartidaNormal();
        p.setId(pj.getId());
        p.setPartida(fromJPA(pj.getPartida()));
        p.setJogador(fromJPA(pj.getJogador()));
        p.setDificuldade(pj.getDificuldade());
        p.setPontuacao(pj.getPontuacao());
        return p;
    }

    public static PartidaMultiJogadorJPA toJPA(PartidaMultiJogador p) {
        PartidaMultiJogadorJPA pj = new PartidaMultiJogadorJPA();
        pj.setId(p.getId());
        pj.setJogo(toJPA(p.getJogo()));
        pj.setDataInicio(p.getDataInicio());
        pj.setDataFim(p.getDataFim());
        pj.setRegiao(toJPA(p.getRegiao()));
        pj.setPartida(toJPA(p.getPartida()));
        pj.setEstado(PartidaMultiJogadorJPA.Estado.valueOf(p.getEstado().name()));
        return pj;
    }

    public static PartidaMultiJogador fromJPA(PartidaMultiJogadorJPA pj) {
        PartidaMultiJogador p = new PartidaMultiJogador();
        p.setId(pj.getId());
        p.setJogo(fromJPA(pj.getJogo()));
        p.setDataInicio(pj.getDataInicio());
        p.setDataFim(pj.getDataFim());
        p.setRegiao(fromJPA(pj.getRegiao()));
        p.setPartida(fromJPA(pj.getPartida()));
        p.setEstado(PartidaMultiJogador.Estado.valueOf(pj.getEstado().name()));
        return p;
    }

    public static ParticipacaoJPA toJPA(Participacao p) {
        ParticipacaoJPA pj = new ParticipacaoJPA();
        ParticipacaoPK pk = new ParticipacaoPK();
        pk.setJogador(p.getJogador().getId());
        pk.setPartidaMulti(p.getPartidaMulti().getId());
        pj.setId(pk);
        pj.setJogador(toJPA(p.getJogador()));
        pj.setPartidaMulti(toJPA(p.getPartidaMulti()));
        pj.setPontuacao(p.getPontuacao());
        return pj;
    }

    public static Participacao fromJPA(ParticipacaoJPA pj) {
        Participacao p = new Participacao();
        p.setJogador(fromJPA(pj.getJogador()));
        p.setPartidaMulti(fromJPA(pj.getPartidaMulti()));
        p.setPontuacao(pj.getPontuacao());
        return p;
    }

    public static CrachaJPA toJPA(Cracha c) {
        CrachaJPA cj = new CrachaJPA();
        cj.setId(c.getId());
        cj.setJogo(toJPA(c.getJogo()));
        cj.setNome(c.getNome());
        cj.setLimitePontos(c.getLimitePontos());
        cj.setUrlImagem(c.getUrlImagem());
        return cj;
    }

    public static Cracha fromJPA(CrachaJPA cj) {
        Cracha c = new Cracha();
        c.setId(cj.getId());
        c.setJogo(fromJPA(cj.getJogo()));
        c.setNome(cj.getNome());
        c.setLimitePontos(cj.getLimitePontos());
        c.setUrlImagem(cj.getUrlImagem());
        return c;
    }

    public static CrachaJogadorJPA toJPA(CrachaJogador c) {
        CrachaJogadorJPA cj = new CrachaJogadorJPA();
        CrachaJogadorPK pk = new CrachaJogadorPK();
        pk.setJogador(c.getJogador().getId());
        pk.setCracha(c.getCracha().getId());
        cj.setId(pk);
        cj.setJogador(toJPA(c.getJogador()));
        cj.setCracha(toJPA(c.getCracha()));
        return cj;
    }

    public static CrachaJogador fromJPA(CrachaJogadorJPA cj) {
        CrachaJogador c = new CrachaJogador();
        c.setJogador(fromJPA(cj.getJogador()));
        c.setCracha(fromJPA(cj.getCracha()));
        return c;
    }

    public static AmizadeJPA toJPA(Amizade a) {
        AmizadeJPA aj = new AmizadeJPA();
        AmizadePK pk = new AmizadePK();
        pk.setJogadorId1(a.getJogador1().getId());
        pk.setJogadorId2(a.getJogador2().getId());
        aj.setId(pk);
        aj.setJogador1(toJPA(a.getJogador1()));
        aj.setJogador2(toJPA(a.getJogador2()));
        return aj;
    }

    public static Amizade fromJPA(AmizadeJPA aj) {
        Amizade a = new Amizade();
        a.setJogador1(fromJPA(aj.getJogador1()));
        a.setJogador2(fromJPA(aj.getJogador2()));
        return a;
    }


    public static ConversaJPA toJPA(Conversa c) {
        ConversaJPA cj = new ConversaJPA();
        cj.setId(c.getId());
        cj.setNome(c.getNome());
        return cj;
    }

    public static Conversa fromJPA(ConversaJPA cj) {
        Conversa c = new Conversa();
        c.setId(cj.getId());
        c.setNome(cj.getNome());
        return c;
    }

    public static ParticipacaoConversaJPA toJPA(ParticipacaoConversa p) {
        ParticipacaoConversaJPA pj = new ParticipacaoConversaJPA();
        ParticipacaoConversaPK pk = new ParticipacaoConversaPK();
        pk.setJogador(p.getJogador().getId());
        pk.setConversa(p.getConversa().getId());
        pj.setId(pk);
        pj.setJogador(toJPA(p.getJogador()));
        pj.setConversa(toJPA(p.getConversa()));
        return pj;
    }

    public static ParticipacaoConversa fromJPA(ParticipacaoConversaJPA pj) {
        ParticipacaoConversa p = new ParticipacaoConversa();
        p.setJogador(fromJPA(pj.getJogador()));
        p.setConversa(fromJPA(pj.getConversa()));
        return p;
    }

    public static MensagemJPA toJPA(Mensagem m) {
        MensagemJPA mj = new MensagemJPA();
        mj.setId(m.getId());
        mj.setConversa(toJPA(m.getConversa()));
        mj.setOrdem(m.getOrdem());
        mj.setJogador(toJPA(m.getJogador()));
        mj.setDataHora(m.getDataHora());
        mj.setTexto(m.getTexto());
        return mj;
    }

    public static Mensagem fromJPA(MensagemJPA mj) {
        Mensagem m = new Mensagem();
        m.setId(mj.getId());
        m.setConversa(fromJPA(mj.getConversa()));
        m.setOrdem(mj.getOrdem());
        m.setJogador(fromJPA(mj.getJogador()));
        m.setDataHora(mj.getDataHora());
        m.setTexto(mj.getTexto());
        return m;
    }

    public static EstatisticasJogadorJPA toJPA(EstatisticasJogador e) {
        EstatisticasJogadorJPA ej = new EstatisticasJogadorJPA();
        ej.setJogador(toJPA(e.getJogador()));
        ej.setNumerosPartidas(e.getNumerosPartidas());
        ej.setNumerosJogos(e.getNumerosJogos());
        ej.setTotalPontos(e.getTotalPontos());

        return ej;
    }

    public static EstatisticasJogador fromJPA(EstatisticasJogadorJPA ej) {
        EstatisticasJogador e = new EstatisticasJogador();
        e.setJogador(fromJPA(ej.getJogador()));
        e.setNumerosPartidas(ej.getNumerosPartidas());
        e.setNumerosJogos(ej.getNumerosJogos());
        e.setTotalPontos(ej.getTotalPontos());
        return e;
    }

    public static EstatisticasJogoJPA toJPA(EstatisticasJogo j) {
        EstatisticasJogoJPA ej = new EstatisticasJogoJPA();
        ej.setJogo(toJPA(j.getJogo()));
        ej.setNumerosPartidas(j.getNumerosPartidas());
        ej.setNumerosJogadores(j.getNumerosJogadores());
        ej.setTotalPontos(j.getTotalPontos());
        return ej;
    }

    public static EstatisticasJogo fromJPA(EstatisticasJogoJPA ej) {
        EstatisticasJogo e = new EstatisticasJogo();
        e.setJogo(fromJPA(ej.getJogo()));
        e.setNumerosPartidas(ej.getNumerosPartidas());
        e.setNumerosJogadores(ej.getNumerosJogadores());
        e.setTotalPontos(ej.getTotalPontos());
        return e;
    }
}