drop procedure if exists juntarConversa(integer, integer);

create or replace procedure juntarConversa(p_jogador_id integer, p_conversa_id integer)
language plpgsql
as $$
begin
	if not exists (select 1 from jogador where id = p_jogador_id) then
        raise exception 'Jogador "%" não existe', p_jogador_id;
    end if;

    if not exists (select 1 from conversa where id = p_conversa_id) then
        raise exception 'Conversa "%" não existe', p_conversa_id;
    end if;
   
    if exists (select 1 from participacao_conversa where jogador_id = p_jogador_id and conversa_id = p_conversa_id) then
    	raise exception 'Jogador "%" já está na conversa "%"', p_jogador_id, p_conversa_id;
    end if;
   
   	set transaction isolation level read committed;
  	
   	begin
		insert into participacao_conversa (jogador_id, conversa_id)
    	values (p_jogador_id, p_conversa_id);
    exception
        when others then
            rollback;
            raise exception 'Erro ao juntar conversa';
    end;
end;
$$;


