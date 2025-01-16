create table regiao (
    id serial primary key,
    nome varchar(255) not null unique
);

create table jogador (
    id serial primary key,
    email varchar(255) not null unique,
    username varchar(255) not null unique,
    estado varchar(7) not null check (estado in ('Ativo', 'Inativo', 'Banido')),
    regiao_id integer not null,
    foreign key (regiao_id) references regiao(id)
);

create table jogo (
    referencia char(10) primary key,
    nome varchar(255) not null unique,
    url varchar(255) not null
);

create table compra (
    jogador_id integer not null,
    jogo_referencia char(10) not null,
    data_compra date not null,
    preco decimal(10, 2) not null,
    primary key (jogador_id, jogo_referencia),
    foreign key (jogador_id) references jogador(id),
    foreign key (jogo_referencia) references jogo(referencia)
);


create table partida (
    id serial primary key,
    jogo_referencia char(10) not null,
    data_inicio timestamp not null,
    data_fim timestamp,
    regiao_id integer not null,
    foreign key (jogo_referencia) references jogo(referencia),
    foreign key (regiao_id) references regiao(id)
);

create table partida_normal (
	partida_id integer primary key,
	jogador_id integer not null,
	dificuldade integer not null check (dificuldade between 1 and 5),
	pontuacao integer not null,
	foreign key (partida_id) references partida(id),
	foreign key (jogador_id) references jogador(id)
);

create table partida_multi_jogador (
	partida_id integer primary key,
	estado varchar(20) not null check (estado in ('Por iniciar', 'A aguardar jogadores', 'Em curso', 'Terminada')),
	foreign key (partida_id) references partida(id)
);


create table participacao (
    jogador_id integer not null,
    partida_multi_id integer not null,
    pontuacao integer not null,
    primary key (jogador_id, partida_multi_id),
    foreign key (jogador_id) references jogador(id),
    foreign key (partida_multi_id) references partida_multi_jogador(partida_id)
);

create table cracha (
    id serial primary key,
    jogo_referencia char(10) not null,
    nome varchar(255) not null unique,
    limite_pontos integer not null,
    url_imagem varchar(255) not null,
    foreign key (jogo_referencia) references jogo(referencia)
);

create table cracha_jogador (
    jogador_id integer not null,
    cracha_id integer not null,
    primary key (jogador_id, cracha_id),
    foreign key (jogador_id) references jogador(id),
    foreign key (cracha_id) references cracha(id)
);

create table amizade (
   jogador_id1 integer not null,
   jogador_id2 integer not null,
   primary key (jogador_id1, jogador_id2),
   foreign key (jogador_id1) references jogador(id),
   foreign key (jogador_id2) references jogador(id)
);

create table conversa (
   id serial primary key,
   nome varchar(255) not null
);

create table participacao_conversa (
	jogador_id integer not null,
	conversa_id integer not null,
	primary key (jogador_id, conversa_id),
	foreign key (jogador_id) references jogador(id),
	foreign key (conversa_id) references conversa(id)
);

create table mensagem (
   	id serial primary key,
   	conversa_id integer not null,
   	ordem integer not null,
   	jogador_id integer not null,
   	data_hora timestamp not null,
   	texto varchar(255) not null,
   	foreign key (conversa_id) references conversa(id),
   	foreign key (jogador_id) references jogador(id),
   	unique (conversa_id, ordem)
);

create table estatisticas_jogador (
    jogador_id integer primary key,
    numero_partidas integer not null,
    numero_jogos integer not null,
    total_pontos integer not null,
    foreign key (jogador_id) references jogador(id)
);

create table estatisticas_jogo (
    jogo_referencia char(10) primary key,
    numero_partidas integer not null,
    numero_jogadores integer not null,
    total_pontos integer not null,
    foreign key (jogo_referencia) references jogo(referencia)
);
