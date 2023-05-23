drop function if exists PontosJogoPorJogador(char(10));

create or replace function PontosJogoPorJogador(p_jogo_referencia char(10))
returns table(jogador_id integer, total_pontos numeric) as $$
begin
    if not exists (select 1 from jogo where referencia = p_jogo_referencia) then
    	raise exception 'Jogo % não existe', p_jogo_referencia;
    end if;
   
   	begin
	    return query
	        select jogadores.jogador_id, sum(jogadores.total_pontos) as total_pontos
	        from (
	            select pn.jogador_id, sum(pn.pontuacao) as total_pontos
	            from partida p
	            join partida_normal pn on p.id = pn.partida_id
	            where p.jogo_referencia = p_jogo_referencia
	            group by pn.jogador_id
	            union
	            select pa.jogador_id, sum(pa.pontuacao) as total_pontos
	            from partida p
	            join partida_multi_jogador pm on p.id = pm.partida_id
	            join participacao pa on pm.partida_id = pa.partida_multi_id
	            where p.jogo_referencia = p_jogo_referencia
	            group by pa.jogador_id
	        ) as jogadores
	        group by jogadores.jogador_id;
	exception
	  	when others then
	   		raise exception 'Erro ao calcular total de pontos do jogo por jogador';
   	end;
end;
$$ language plpgsql; 