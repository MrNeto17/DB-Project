drop function if exists totalJogosJogador(integer);

create or replace function totalJogosJogador(p_jogador_id integer)
returns integer as $$
declare
	total_jogos integer;
begin
  	if not exists (select 1 from jogador where id = p_jogador_id) then
    	raise exception 'Jogador % não existe', p_jogador_id;
    end if;
	
   	begin
	    select count(distinct jogo_referencia)
	    into total_jogos
	    from (
	        select p.jogo_referencia
	        from partida p
	        join partida_normal pn on p.id = pn.partida_id
	        where pn.jogador_id = p_jogador_id
	        union
	        select p.jogo_referencia
	        from partida p
	        join partida_multi_jogador pm on p.id = pm.partida_id
	        join participacao pa on pm.partida_id = pa.partida_multi_id
	        where pa.jogador_id = p_jogador_id
	    ) as jogador_partidas;
	
		return total_jogos;
	exception
	  	when others then
	    	raise exception 'Erro ao calcular o total de jogos do jogador';
	end;
end;
$$ language plpgsql;