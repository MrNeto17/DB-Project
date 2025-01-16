drop trigger if exists tr_banirJogador on jogadorTotalInfo;
drop function if exists banirJogador();

create or replace function banirJogador()
returns trigger as $$
begin
	set transaction isolation level read committed;

	begin
		update jogador
		set estado = 'Banido'
		where id = old.jogador_id;
	exception
		when others then
			rollback;
			raise exception 'Erro ao banir jogador';
	end;
	return old;
end;
$$ language plpgsql;

create trigger tr_banirJogador
instead of delete on jogadorTotalInfo
for each row
execute function banirJogador();