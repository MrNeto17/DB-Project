drop procedure if exists iniciarConversa(integer, varchar(255), integer);

create or replace procedure iniciarConversa(p_jogador_id integer, p_nome_conversa varchar(255), out p_conversa_id integer)
language plpgsql
as $$
begin 
	if not exists (select 1 from jogador where id = p_jogador_id) then
        raise exception 'Jogador "%" não existe', p_jogador_id;
    end if;
   
	set transaction isolation level read committed;

    insert into conversa (nome)
    values (p_nome_conversa)
    returning id into p_conversa_id;

   	begin
    	insert into participacao_conversa (jogador_id, conversa_id)
    	values (p_jogador_id, p_conversa_id);
    exception
    	when others then
    		rollback;
    		raise exception 'Erro ao associar jogador à conversa';
    end;
end;
$$;
