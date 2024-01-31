package com.gameon.businessLogic;

import com.gameon.dal.datascope.DataScope;
import com.gameon.dal.mapper.MapperJogo;
import com.gameon.dal.mapper.MapperRegiao;
import com.gameon.dal.repository.RepositoryRegiao;
import com.gameon.model.Jogo;
import com.gameon.model.Regiao;

import java.util.List;

public class BLService {
    public void teste0() throws Exception {
        // ****************** Teste 0 ****************
        // Mappers - Uma operação por transação

        MapperJogo m = new MapperJogo();
        Jogo j = new Jogo();
        j.setReferencia("JOGO0");
        j.setNome("jogo0");
        j.setUrl("jogo0.com");
        m.create(j);
    }

    public void teste1() throws Exception {
        // ****************** Teste 1 ****************
        // Mappers - várias operações na mesma transação

        try (DataScope ds = new DataScope()) {
            MapperRegiao mr = new MapperRegiao();
            Regiao regiao = new Regiao();
            regiao.setNome("regiao1");
            mr.create(regiao);
            MapperJogo mj = new MapperJogo();
            Jogo jogo = new Jogo();
            jogo.setReferencia("JOGO1");
            jogo.setNome("jogo1");
            jogo.setUrl("jogo1.com");
            mj.create(jogo);
            ds.validateWork();
        }
    }


    public void teste2() throws Exception {
        // ****************** Teste 2 ****************
        // Repositories - várias operações na mesma transação

        try (DataScope ds = new DataScope()) {
            RepositoryRegiao repository = new RepositoryRegiao();
            Regiao regiao = new Regiao();
            regiao.setId(1);
            regiao.setNome("regiao2");
            repository.add(regiao);
            regiao = repository.find(1);
            System.out.printf("IdRegiao:%d, nomeRegiao:%s\n", regiao.getId(), regiao.getNome());
            regiao.setNome("nao regiao2");
            repository.save(regiao);
            regiao = repository.find(regiao.getId());
            System.out.printf("IdRegiao:%d, nomeRegiao:%s\n", regiao.getId(), regiao.getNome());
            //repository.delete(regiao);
            List<Regiao> listaRegiao = repository.getAll();
            for (Regiao x : listaRegiao) {
                System.out.printf("IdRegiao:%d, nomeRegiao:%s\n", x.getId(), x.getNome());
            }
            ds.validateWork();
        }
    }


    public void teste3() throws Exception {
        // ****************** Teste 3 ****************
        // Uso direto de JPA
        System.out.println("Comm implementa��o baseda em JDBC n�o � poss�vel usar JPA");
    }


    public void teste4() throws Exception {
        // ****************** Teste 4 ****************
        // Uso como UnifOfWork

        try (DataScope ds = new DataScope()) {
            Regiao r = new Regiao();
            r.setId(1);
            r.setNome("regiao4");
            ds.insertRegiao(r);
            r = ds.findRegiao(1);
            System.out.printf("id regiao: %d, nome regiao: %s", r.getId(), r.getNome());
            r.setNome("nao regiao 4");
            ds.updateRegiao(r);
            ds.validateWork();
        }
    }

    public void teste5() throws Exception {
        // ****************** Teste 5 ****************
        // Reutilização de processos de negócio
        try (DataScope ds = new DataScope()) {
            Regiao r = new Regiao();
            r.setId(2);
            r.setNome("regiao5");
            ds.insertRegiao(r);
            teste4();
            r = ds.findRegiao(2);
            System.out.printf("id regiao: %d, nome regiao: %s", r.getId(), r.getNome());
            r.setNome("nao regiao 5");
            ds.validateWork();
        }

    }
}

