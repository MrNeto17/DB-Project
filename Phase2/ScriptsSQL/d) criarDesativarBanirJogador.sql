drop function if exists criarJogador(varchar, varchar, integer);
drop function if exists desativarJogador(integer);
drop function if exists banirJogador(integer);

create or replace function criarJogador(p_email varchar, p_username varchar, p_regiao_id integer)
returns void as $$
declare
begin
	if exists (select 1 from jogador where email = p_email) then
		raise exception 'Email "%" já existe', p_email;
	end if;

	if exists (select 1 from jogador where username = p_username) then
		raise exception 'Username "%" já existe', p_username;
	end if;

	if not exists (select 1 from regiao where id = p_regiao_id) then 
		raise exception 'Região "%" não existe', p_regiao_id;
	end if;

	set transaction isolation level read committed;
	
	begin
		insert into jogador (email, username, estado, regiao_id)
  		values (p_email, p_username, 'Ativo', p_regiao_id);
	exception
  		when others then
  			rollback;
  			raise exception 'Erro ao criar jogador';		
    end;
end;
$$ language plpgsql;

create or replace function desativarJogador(p_jogador_id integer)
returns void as $$
declare
begin
	if not exists (select 1 from jogador where id = p_jogador_id) then
		raise exception 'Jogador % não existe', p_jogador_id;
	end if;

	set transaction isolation level read committed;
	
	begin
  		update jogador
  		set estado = 'Inativo'
  		where id = p_jogador_id;
	exception
  		when others then
    		rollback;
  			raise exception 'Erro ao desativar jogador';
  	end;
end;
$$ language plpgsql;

create or replace function banirJogador(p_jogador_id integer)
returns void as $$
declare
begin
	if not exists (select 1 from jogador where id = p_jogador_id) then
		raise exception 'Jogador % não existe', p_jogador_id;
	end if;

	set transaction isolation level read committed;
	
	begin
  		update jogador
  		set estado = 'Banido'
  		where id = p_jogador_id;
	exception
  		when others then
    		rollback;
  			raise exception 'Erro ao banir jogador';
  	end;
end;
$$ language plpgsql;