insert into regiao (nome) values
    ('regiao1'),
    ('regiao2'),
    ('regiao3');

insert into jogador (email, username, estado, regiao_id) values
    ('jogador1@gmail.com', 'jogador1', 'Ativo', 1),
    ('jogador2@gmail.com', 'jogador2', 'Inativo', 2),
    ('jogador3@gmail.com', 'jogador3', 'Banido', 3),
    ('jogador4@gmail.com', 'jogador4', 'Ativo', 1),
    ('jogador5@gmail.com', 'jogador5', 'Ativo', 1),
    ('jogador6@gmail.com', 'jogador6', 'Ativo', 1);


insert into jogo (referencia, nome, url) values
    ('jogo001', 'jogo1', 'jogo1.com'),
    ('jogo002', 'jogo2', 'jogo2.com'),
    ('jogo003', 'jogo3', 'jogo3.com');

insert into compra (jogador_id, jogo_referencia, data_compra, preco) values
    (1, 'jogo001', '2023-01-01', 19.99),
    (1, 'jogo002', '2023-02-01', 29.99),
    (2, 'jogo002', '2023-02-01', 29.99),
    (3, 'jogo003', '2023-03-01', 39.99),
    (4, 'jogo001', '2023-01-01', 19.99),
    (5, 'jogo001', '2023-01-01', 19.99),
    (6, 'jogo001', '2023-01-01', 19.99),
    (6, 'jogo002', '2023-02-01', 29.99);

insert into partida (jogo_referencia, data_inicio, data_fim, regiao_id) values
    ('jogo001', '2023-01-11 11:00:00', null, 1),
    ('jogo001', '2023-02-11 12:00:00', '2023-02-11 13:00:00', 1),
    ('jogo001', '2023-03-11 13:00:00', '2023-03-11 14:00:00', 1),
    ('jogo001', '2023-04-11 14:00:00', '2023-04-11 15:00:00', 1),
    ('jogo001', '2023-05-11 15:00:00', '2023-05-11 16:00:00', 1),
    ('jogo001', '2023-06-11 16:00:00', '2023-06-11 17:00:00', 1),
    ('jogo001', '2023-07-11 17:00:00', '2023-07-11 18:00:00', 1),
    ('jogo002', '2023-08-11 18:00:00', '2023-08-11 19:00:00', 1);
   
insert into partida_normal (partida_id, jogador_id, dificuldade, pontuacao) values
    (1, 1, 1, 100),
    (2, 4, 2, 200),
    (3, 5, 3, 300),
    (4, 6, 4, 400);
   

insert into partida_multi_jogador (partida_id, estado) values
    (5, 'Por iniciar'),
    (6, 'A aguardar jogadores'),
    (7, 'Em curso'),
    (8, 'Terminada');

insert into participacao (jogador_id, partida_multi_id, pontuacao) values
    (1, 5, 0),
    (4, 5, 0),
    (5, 6, 0),
    (6, 6, 0),
    (1, 7, 100),
    (5, 7, 500),
   	(1, 8, 100),
    (6, 8, 600);

insert into cracha (jogo_referencia, nome, limite_pontos, url_imagem) values
    ('jogo001', 'bronze1', 100, 'jogo1.com/bronze1'),
    ('jogo001', 'prata1', 200, 'jogo1.com/prata1'),
    ('jogo001', 'ouro1', 300, 'jogo1.com/ouro1'),
    ('jogo002', 'bronze2', 100, 'jogo2.com/bronze2'),
    ('jogo002', 'prata2', 200, 'jogo2.com/prata2'),
    ('jogo002', 'ouro2', 300, 'jogo2.com/ouro2'),
    ('jogo003', 'bronze3', 100, 'jogo3.com/bronze3'),
    ('jogo003', 'prata3', 200, 'jogo3.com/prata3'),
    ('jogo003', 'ouro3', 300, 'jogo3.com/ouro3');

insert into cracha_jogador (jogador_id, cracha_id) values
    (1, 5),
    (1, 6),
    (4, 5),
    (4, 6),
    (5, 6);

insert into conversa (nome) values
    ('conversa1'),
    ('conversa2'),
    ('conversa3');

insert into participacao_conversa (jogador_id, conversa_id) values
    (4, 1),
    (5, 1),
    (6, 1),
    (1, 2),
    (4, 2),
    (5, 3),
    (6, 3);

insert into mensagem (conversa_id, ordem, jogador_id, data_hora, texto) values
    (1, 1, 4, '2024-01-01 11:00:00', 'boas'),
    (1, 2, 5, '2024-01-01 11:00:00', 'atao'),
    (1, 3, 6, '2024-01-01 11:00:00', 'rei'),
    (2, 1, 1, '2024-01-01 11:00:00', 'ajuda'),
    (2, 2, 4, '2024-01-01 11:00:00', 'nao'),
    (3, 1, 5, '2024-01-01 11:00:00', 'encontrei'),
    (3, 2, 6, '2024-01-01 11:00:00', 'han');


