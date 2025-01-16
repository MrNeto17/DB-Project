package com.gameon.dal.datascope;

import com.gameon.dal.abstractdatascope.AbstractDataScope;
import com.gameon.dal.mapper.*;
import com.gameon.dal.modelJPA.*;
import com.gameon.dal.repository.*;
import com.gameon.model.*;

import java.util.List;

public class DataScope extends AbstractDataScope implements AutoCloseable {

    public DataScope() {
        super();
    }

    public List<Regiao> getAllRegioes() throws Exception {
        return new RepositoryRegiao().getAll();
    }

    public Regiao findRegiao(int id) throws Exception {
        return new MapperRegiao().read(id);
    }

    public void deleteRegiao(Regiao r) throws Exception {
        new MapperRegiao().delete(r);
    }

    public void deleteRegiaoByKey(int id) throws Exception {
        Regiao r = new Regiao();
        r.setId(id);
        new MapperRegiao().delete(r);
    }

    public void updateRegiao(Regiao r) throws Exception {
        new MapperRegiao().update(r);
    }

    public void insertRegiao(Regiao r) throws Exception {
        new MapperRegiao().create(r);
    }

    public List<Jogador> getAllJogadores() throws Exception {
        return new RepositoryJogador().getAll();
    }

    public Jogador findJogador(int id) throws Exception {
        return new MapperJogador().read(id);
    }

    public void deleteJogador(Jogador jogador) throws Exception {
        new MapperJogador().delete(jogador);
    }

    public void deleteJogadorById(int id) throws Exception {
        Jogador jogador = new Jogador();
        jogador.setId(id);
        new MapperJogador().delete(jogador);
    }

    public void updateJogador(Jogador jogador) throws Exception {
        new MapperJogador().update(jogador);
    }

    public void insertJogador(Jogador jogador) throws Exception {
        new MapperJogador().create(jogador);
    }

    public List<Jogo> getAllJogos() throws Exception {
        return new RepositoryJogo().getAll();
    }

    public Jogo findJogo(String id) throws Exception {
        return new MapperJogo().read(id);
    }

    public void deleteJogo(Jogo jogo) throws Exception {
        new MapperJogo().delete(jogo);
    }

    public void deleteJogoById(String id) throws Exception {
        Jogo jogo = new Jogo();
        jogo.setReferencia(id);
        new MapperJogo().delete(jogo);
    }

    public void updateJogo(Jogo jogo) throws Exception {
        new MapperJogo().update(jogo);
    }

    public void insertJogo(Jogo jogo) throws Exception {
        new MapperJogo().create(jogo);
    }

    public List<Compra> getAllCompras() throws Exception {
        return new RepositoryCompra().getAll();
    }

    public Compra findCompra(CompraPK id) throws Exception {
        return new MapperCompra().read(id);
    }

    public void deleteCompra(Compra compra) throws Exception {
        new MapperCompra().delete(compra);
    }

    public void deleteCompraById(Jogo gameId, Jogador userId) throws Exception {
        Compra compra = new Compra();
        compra.setJogo(gameId);
        compra.setJogador(userId);
        new MapperCompra().delete(compra);
    }

    public void updateCompra(Compra compra) throws Exception {
        new MapperCompra().update(compra);
    }

    public void insertCompra(Compra compra) throws Exception {
        new MapperCompra().create(compra);
    }

    public List<Partida> getAllPartidas() throws Exception {
        return new RepositoryPartida().getAll();
    }

    public Partida findPartida(int id) throws Exception {
        return new MapperPartida().read(id);
    }

    public void deletePartida(Partida partida) throws Exception {
        new MapperPartida().delete(partida);
    }

    public void deletePartidaById(int id) throws Exception {
        Partida partida = new Partida();
        partida.setId(id);
        new MapperPartida().delete(partida);
    }

    public void updatePartida(Partida partida) throws Exception {
        new MapperPartida().update(partida);
    }

    public void insertPartida(Partida partida) throws Exception {
        new MapperPartida().create(partida);
    }

    public List<PartidaNormal> getAllPartidasNormais() throws Exception {
        return new RepositoryPartidaNormal().getAll();
    }

    public PartidaNormal findPartidaNormal(Partida id) throws Exception {
        return new MapperPartidaNormal().read(id);
    }

    public void deletePartidaNormal(PartidaNormal partida) throws Exception {
        new MapperPartidaNormal().delete(partida);
    }

    public void deletePartidaById(Partida id) throws Exception {
        PartidaNormal partida = new PartidaNormal();
        partida.setPartida(id);
        new MapperPartidaNormal().delete(partida);
    }

    public void updatePartidaNormal(PartidaNormal partida) throws Exception {
        new MapperPartidaNormal().update(partida);
    }

    public void insertPartidaNormal(PartidaNormal partida) throws Exception {
        new MapperPartidaNormal().create(partida);
    }

    public List<PartidaMultiJogador> getAllPartidasMultiJogador() throws Exception {
        return new RepositoryPartidaMultiJogador().getAll();
    }

    public PartidaMultiJogador findPartidaMultiJogador(Partida id) throws Exception {
        return new MapperPartidaMultiJogador().read(id);
    }

    public void deletePartidaMultiJogador(PartidaMultiJogador partida) throws Exception {
        new MapperPartidaMultiJogador().delete(partida);
    }

    public void deletePartidaMultiJogadorById(PartidaMultiJogador id) throws Exception {
        PartidaMultiJogador partida = new PartidaMultiJogador();
        partida.setPartida(id);
        new MapperPartidaMultiJogador().delete(partida);
    }

    public void updatePartidaMultiJogador(PartidaMultiJogador partida) throws Exception {
        new MapperPartidaMultiJogador().update(partida);
    }

    public void insertPartidaMultiJogador(PartidaMultiJogador partida) throws Exception {
        new MapperPartidaMultiJogador().create(partida);
    }

    public List<Participacao> getAllParticipacoes() throws Exception {
        return new RepositoryParticipacao().getAll();
    }

    public Participacao findParticipacao(ParticipacaoPK id) throws Exception {
        return new MapperParticipacao().read(id);
    }

    public void deleteParticipacao(Participacao participacao) throws Exception {
        new MapperParticipacao().delete(participacao);
    }

    public void deleteParticipacaoById(Partida partida, Jogador jogador) throws Exception {
        Participacao participacao = new Participacao();
        participacao.setPartidaMulti(partida);
        participacao.setJogador(jogador);
        new MapperParticipacao().delete(participacao);
    }

    public void updateParticipacao(Participacao participacao) throws Exception {
        new MapperParticipacao().update(participacao);
    }

    public void insertParticipacao(Participacao participacao) throws Exception {
        new MapperParticipacao().create(participacao);
    }

    public List<Cracha> getAllCrachas() throws Exception {
        return new RepositoryCracha().getAll();
    }

    public Cracha findCracha(int id) throws Exception {
        return new MapperCracha().read(id);
    }

    public void deleteCracha(Cracha cracha) throws Exception {
        new MapperCracha().delete(cracha);
    }

    public void deleteCrachaById(int id) throws Exception {
        Cracha cracha = new Cracha();
        cracha.setId(id);
        new MapperCracha().delete(cracha);
    }

    public void updateCracha(Cracha cracha) throws Exception {
        new MapperCracha().update(cracha);
    }

    public void insertCracha(Cracha cracha) throws Exception {
        new MapperCracha().create(cracha);
    }

    public List<CrachaJogador> getAllCrachaJogadores() throws Exception {
        return new RepositoryCrachaJogador().getAll();
    }

    public CrachaJogador findCrachaJogador(CrachaJogadorPK id) throws Exception {
        return new MapperCrachaJogador().read(id);
    }

    public void deleteCrachaJogador(CrachaJogador crachaJogador) throws Exception {
        new MapperCrachaJogador().delete(crachaJogador);
    }

    public void deleteCrachJogadorById(Cracha crachaId, Jogador jogadorId) throws Exception {
        CrachaJogador crachaJogador = new CrachaJogador();
        crachaJogador.setCracha(crachaId);
        crachaJogador.setJogador(jogadorId);
        new MapperCrachaJogador().delete(crachaJogador);
    }

    public void updateCrachaJogador(CrachaJogador crachaJogador) throws Exception {
        new MapperCrachaJogador().update(crachaJogador);
    }

    public void insertCrachaJogador(CrachaJogador crachaJogador) throws Exception {
        new MapperCrachaJogador().create(crachaJogador);
    }

    public List<Amizade> getAllAmizades() throws Exception {
        return new RepositoryAmizade().getAll();
    }

    public Amizade findAmizade(AmizadePK jogador) throws Exception {
        return new MapperAmizade().read(jogador);
    }

    public void deleteAmizade(Amizade amizade) throws Exception {
        new MapperAmizade().delete(amizade);
    }

    public void deleteAmizadeById(Jogador jogador1, Jogador jogador2) throws Exception {
        Amizade amizade = new Amizade();
        amizade.setJogador1(jogador1);
        amizade.setJogador2(jogador2);
        new MapperAmizade().delete(amizade);
    }

    public void updateAmizade(Amizade amizade) throws Exception {
        new MapperAmizade().update(amizade);
    }

    public void insertAmizade(Amizade amizade) throws Exception {
        new MapperAmizade().create(amizade);
    }

    public List<Conversa> getAllConversas() throws Exception {
        return new RepositoryConversa().getAll();
    }

    public Conversa findConversa(int id) throws Exception {
        return new MapperConversa().read(id);
    }

    public void deleteConversa(Conversa conversa) throws Exception {
        new MapperConversa().delete(conversa);
    }

    public void deleteConversaById(int id) throws Exception {
        Conversa conversa = new Conversa();
        conversa.setId(id);
        new MapperConversa().delete(conversa);
    }

    public void updateConversa(Conversa conversa) throws Exception {
        new MapperConversa().update(conversa);
    }

    public void insertConversa(Conversa conversa) throws Exception {
        new MapperConversa().create(conversa);
    }

    public List<ParticipacaoConversa> getAllParticipacoesConversa() throws Exception {
        return new RepositoryParticipacaoConversa().getAll();
    }

    public ParticipacaoConversa findParticipacaoConversa(ParticipacaoConversaPK id) throws Exception {
        return new MapperParticipacaoConversa().read(id);
    }

    public void deleteParticipacaoConversa(ParticipacaoConversa participacaoConversa) throws Exception {
        new MapperParticipacaoConversa().delete(participacaoConversa);
    }

    public void deleteParticipacaoConversaById(Conversa conversa, Jogador jogador) throws Exception {
        ParticipacaoConversa participacaoConversa = new ParticipacaoConversa();
        participacaoConversa.setConversa(conversa);
        participacaoConversa.setJogador(jogador);
        new MapperParticipacaoConversa().delete(participacaoConversa);
    }

    public void updateParticipacaoConversa(ParticipacaoConversa participacaoConversa) throws Exception {
        new MapperParticipacaoConversa().update(participacaoConversa);
    }

    public void insertParticipacaoConversa(ParticipacaoConversa participacaoConversa) throws Exception {
        new MapperParticipacaoConversa().create(participacaoConversa);
    }

    public List<Mensagem> getAllMensagens() throws Exception {
        return new RepositoryMensagem().getAll();
    }

    public Mensagem findMensagem(int id) throws Exception {
        return new MapperMensagem().read(id);
    }

    public void deleteMensagem(Mensagem mensagem) throws Exception {
        new MapperMensagem().delete(mensagem);
    }

    public void deleteMensagemById(int id) throws Exception {
        Mensagem mensagem = new Mensagem();
        mensagem.setId(id);
        new MapperMensagem().delete(mensagem);
    }

    public void updateMensagem(Mensagem mensagem) throws Exception {
        new MapperMensagem().update(mensagem);
    }

    public void insertMensagem(Mensagem mensagem) throws Exception {
        new MapperMensagem().create(mensagem);
    }

    public List<EstatisticasJogador> getAllEstatisticasJogador() throws Exception {
        return new RepositoryEstatisticasJogador().getAll();
    }

    public EstatisticasJogador findEstatisticasJogador(Jogador id) throws Exception {
        return new MapperEstatisticasJogador().read(id);
    }

    public void deleteEstatisticasJogador(EstatisticasJogador estatisticasJogador) throws Exception {
        new MapperEstatisticasJogador().delete(estatisticasJogador);
    }

    public void deleteEstatisticasJogadorById(Jogador jogador) throws Exception {
        EstatisticasJogador estatisticasJogador = new EstatisticasJogador();
        estatisticasJogador.setJogador(jogador);
        new MapperEstatisticasJogador().delete(estatisticasJogador);
    }

    public void updateEstatisticasJogador(EstatisticasJogador estatisticasJogador) throws Exception {
        new MapperEstatisticasJogador().update(estatisticasJogador);
    }

    public void insertEstatisticasJogador(EstatisticasJogador estatisticasJogador) throws Exception {
        new MapperEstatisticasJogador().create(estatisticasJogador);
    }

    public List<EstatisticasJogo> getAllEstatisticasJogo() throws Exception {
        return new RepositoryEstatisticasJogo().getAll();
    }

    public EstatisticasJogo findEstatisticasJogo(Jogo id) throws Exception {
        return new MapperEstatisticasJogo().read(id);
    }

    public void deleteEstatisticasJogo(EstatisticasJogo estatisticasJogo) throws Exception {
        new MapperEstatisticasJogo().delete(estatisticasJogo);
    }

    public void deleteEstatisticasJogoById(Jogo jogo) throws Exception {
        EstatisticasJogo estatisticasJogo = new EstatisticasJogo();
        estatisticasJogo.setJogo(jogo);
        new MapperEstatisticasJogo().delete(estatisticasJogo);
    }

    public void updateEstatisticasJogo(EstatisticasJogo estatisticasJogo) throws Exception {
        new MapperEstatisticasJogo().update(estatisticasJogo);
    }

    public void insertEstatisticasJogo(EstatisticasJogo estatisticasJogo) throws Exception {
        new MapperEstatisticasJogo().create(estatisticasJogo);
    }

}