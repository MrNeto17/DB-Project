drop procedure if exists enviarMensagem(integer, integer, varchar(255));

create or replace procedure enviarMensagem(p_jogador_id integer, p_conversa_id integer, p_texto varchar(255))
language plpgsql
as $$
declare
    max_ordem integer;
begin
    if not exists (select 1 from jogador where id = p_jogador_id) then
        raise exception 'Jogador "%" não existe', p_jogador_id;
    end if;

    if not exists (select 1 from conversa where id = p_conversa_id) then
        raise exception 'Conversa "%" não existe', p_conversa_id;
    end if;
   
    if not exists (select 1 from participacao_conversa where jogador_id = p_jogador_id and conversa_id = p_conversa_id) then
    	raise exception 'Jogador "%" não está na conversa "%"', p_jogador_id, p_conversa_id;
    end if;
   
    set transaction isolation level read committed;
   
    begin
	    select max(ordem) into max_ordem
	    from mensagem
	    where conversa_id = p_conversa_id;
	
	    if max_ordem is null then
	        max_ordem := 1;
	    else
	        max_ordem := max_ordem + 1;
	    end if;
		
        insert into mensagem (conversa_id, ordem, jogador_id, data_hora, texto)
        values (p_conversa_id, max_ordem, p_jogador_id, now(), p_texto); 
 	exception
        when others then
            rollback;
            raise exception 'Erro ao enviar mensagem';
	end;
end;
$$;

call enviarmensagem(1, 2, 'a') ;
