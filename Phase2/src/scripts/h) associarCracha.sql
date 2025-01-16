drop procedure if exists associarCracha(integer, char(10), varchar(255));

create or replace procedure associarCracha(p_jogador_id integer, p_jogo_referencia char(10), p_nome_cracha varchar(255))
language plpgsql
as $$
declare
    v_limite_pontos integer;
    v_total_pontos integer;
    v_cracha_id integer;
begin
	if not exists (select 1 from jogador where id = p_jogador_id) then 
		raise exception 'Jogador "%" não existe', p_jogador_id;
	end if;

	if not exists (select 1 from jogo where referencia = p_jogo_referencia) then
    	raise exception 'Jogo "%" não existe', p_jogo_referencia;
    end if;
   
   	if not exists (select 1 from cracha where nome = p_nome_cracha) then 
   		raise exception 'Cracha "%" não existe', p_nome_cracha;
   	end if;
   
	set transaction isolation level read committed;

	select id, limite_pontos into v_cracha_id, v_limite_pontos
    from cracha
    where jogo_referencia = p_jogo_referencia and nome = p_nome_cracha;

    if v_limite_pontos is null then
        raise exception 'Crachá "%" não encontrado para o jogo "%"', p_nome_cracha, p_jogo_referencia;
    end if;
   
	select sum(pontuacao) into v_total_pontos
    from (
        select pontuacao
        from partida_normal
        join partida on partida_normal.partida_id = partida.id
        where partida.jogo_referencia = p_jogo_referencia and jogador_id = p_jogador_id
        union
        select pontuacao
        from participacao
        join partida_multi_jogador on participacao.partida_multi_id = partida_multi_jogador.partida_id
        join partida on partida_multi_jogador.partida_id = partida.id
        where partida.jogo_referencia = p_jogo_referencia and jogador_id = p_jogador_id
    ) as pontos_jogador_jogo;
   
    if v_total_pontos is null then
    	v_total_pontos := 0;
    end if;
   
	begin
	   	if v_total_pontos >= v_limite_pontos then
	        if not exists (select 1 from cracha_jogador where jogador_id = p_jogador_id and cracha_id = v_cracha_id) then
	            insert into cracha_jogador (jogador_id, cracha_id)
	            values (p_jogador_id, v_cracha_id);
	        end if;
	   	end if;
	exception
		when others then
		  	rollback;
		   	raise exception 'Erro ao associar cráchas';
	end;
end;
$$;