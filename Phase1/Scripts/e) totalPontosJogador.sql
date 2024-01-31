drop function if exists totalPontosJogador(integer);

create or replace function totalPontosJogador(p_jogador_id integer)
returns integer as $$
declare
	total_pontos_normal integer;
  	total_pontos_multi integer;
begin
	if not exists (select 1 from jogador where id = p_jogador_id) then
		raise exception 'Jogador % não existe', p_jogador_id;
	end if;

	begin
		select sum(pontuacao) into total_pontos_normal
	  	from partida_normal
	  	where jogador_id = p_jogador_id;
	
	  	select sum(pontuacao) into total_pontos_multi
	  	from participacao
	  	where jogador_id = p_jogador_id;
	exception
	  	when others then
	    	raise exception 'Erro ao calcular total de pontos do jogador';
 	end;
 
  	if total_pontos_normal is null then
		total_pontos_normal := 0;
	end if;

	if total_pontos_multi is null then
		total_pontos_multi := 0;
	end if;
	
	return total_pontos_normal + total_pontos_multi;
end;
$$ language plpgsql;