drop trigger if exists tr_atribuirCrachas on partida;
drop function if exists atribuirCrachas();

create or replace function atribuirCrachas()
returns trigger as $$
declare
  	v_cracha_nome varchar(255);
  	v_jogador_id integer;
begin
	set transaction isolation level read committed;

	SELECT jogador_id into v_jogador_id
    from partida_normal
    where partida_id = new.id;

    if not found then
        select jogador_id into v_jogador_id
        from participacao
        join partida_multi_jogador on participacao.partida_multi_id = partida_multi_jogador.partida_id
        join partida on partida_multi_jogador.partida_id = partida.id
        where partida_multi_jogador.partida_id = new.id;
    end if;
   
	begin
		for v_cracha_nome in (select nome from cracha where jogo_referencia = new.jogo_referencia) 
			loop
				call associarCracha(v_jogador_id, new.jogo_referencia, v_cracha_nome);
			end loop;
	exception
 	   when others then
        	rollback;
        	raise exception 'Erro ao atribuir crachas da partida.';
    end;
   
	return new;
end;
$$ language plpgsql;

create trigger tr_atribuirCrachas
after update of data_fim on partida
for each row
when (old.data_fim is null and new.data_fim is not null)
execute function atribuirCrachas();

