create or replace function teste(numero integer, descricao varchar(255), resultado boolean)
returns void as $$
begin
 	raise notice 'Teste %: %: %', numero, descricao, case
    	when resultado then 'Resultado OK'
    	else 'Resultado FAIL'
  	end;
end;
$$ language plpgsql;

create or replace function simular_erro_insercao()
returns trigger as $$
begin
  	raise exception 'Erro simulado na inserção';
end;
$$ language plpgsql;

do $$
declare
  	resultado boolean;
  	v_output integer;
  	v_output2 integer;
  	v_ordem integer;
begin
	perform criarJogador('jogador7@gmail.com', 'jogador7', 1);
	select exists (
		select 1 
		from jogador 
		where email = 'jogador7@gmail.com' and username = 'jogador7' and regiao_id = 1
	) into resultado;
	perform teste(1, 'Criar jogador com parâmetros possíveis', resultado);

	begin
    	resultado := false;
    	perform criarJogador('jogador1@gmail.com', 'j', 1);
  	exception
    	when others then
      		select not exists (
				select 1 
				from jogador 
				where email = 'jogador1@gmail.com' and username = 'j' and regiao_id = 1
			) into resultado;
  	end;
  	perform teste(2, 'Erro ao criar jogador com email já existente', resultado);
  	
  	begin
    	resultado := false;
    	perform criarJogador('jogador9@gmail.com', 'jogador1', 1);
  	exception
    	when others then
      		select not exists (
				select 1 
				from jogador 
				where email = 'jogador9@gmail.com' and username = 'jogador1' and regiao_id = 1
			) into resultado;
  	end;
  	perform teste(3, 'Erro ao criar jogador com username já existente', resultado);
  
  	begin
    	resultado := false;
    	perform criarJogador('jogador8@gmail.com', 'jogador8', 4);
  	exception
    	when others then
      		select not exists (
				select 1 
				from jogador 
				where email = 'jogador8@gmail.com' and username = 'jogador8' and regiao_id = 4
			) into resultado;
  	end;
  	perform teste(4, 'Erro ao criar jogador com região não existente', resultado);
  
  	create trigger simular_erro_insercao_trigger
	before insert on jogador
	for each row execute function simular_erro_insercao();
  	
  	begin
    	resultado := false;
    	perform criarJogador('jogador9@gmail.com', 'jogador9', 1);
  	exception
    	when others then
      		select not exists (
				select 1 
				from jogador 
				where email = 'jogador9@gmail.com' and username = 'jogador9' and regiao_id = 1
			) into resultado;
  	end;
  	perform teste(5, 'Transação revertida corretamente', resultado);
  
  	drop trigger simular_erro_insercao_trigger on jogador;
  
  	perform desativarJogador(6);
  	select exists (
  		select 1 
  		from jogador 
  		where email = 'jogador6@gmail.com' and username = 'jogador6' and regiao_id = 1 and estado = 'Inativo'
  	) into resultado;
  	perform teste(6, 'Desativar jogador existente', resultado);
  
  	begin
  		resultado := false;
  		perform desativarJogador(99);
  	exception
  		when others then
  			select not exists (
  				select 1
  				from jogador
  				where id = 99 and estado = 'Desativado'
  			) into resultado;
  	end;
  	perform teste(7, 'Erro ao desativar jogador com id inexistente', resultado);
  
  	create trigger simular_erro_insercao_trigger
	before update on jogador
	for each row execute function simular_erro_insercao();
	
	begin
  		resultado := false;
  		perform desativarJogador(1);
  	exception
  		when others then
  			select not exists (
  				select 1
  				from jogador
  				where id = 1 and estado = 'Desativado'
  			) into resultado;
  	end;
  	perform teste(8, 'Transação revertida corretamente', resultado);
	
	drop trigger simular_erro_insercao_trigger on jogador;

  	perform banirJogador(6);
  	select exists (
  		select 1 
  		from jogador 
  		where email = 'jogador6@gmail.com' and username = 'jogador6' and regiao_id = 1 and estado = 'Banido'
  	) into resultado;
  	perform teste(9, 'Banir jogador existente', resultado);
  
  	begin
  		resultado := false;
  		perform banirJogador(99);
  	exception
  		when others then
  			select not exists (
  				select 1
  				from jogador
  				where id = 99 and estado = 'Banido'
  			) into resultado;
  	end;
  	perform teste(10, 'Erro ao banir jogador com id inexistente', resultado);
  
  	create trigger simular_erro_insercao_trigger
	before update on jogador
	for each row execute function simular_erro_insercao();

	begin
  		resultado := false;
  		perform banirJogador(1);
  	exception
  		when others then
  			select not exists (
  				select 1
  				from jogador
  				where id = 1 and estado = 'Banido'
  			) into resultado;
  	end;
  	perform teste(11, 'Transação revertida corretamente', resultado);
  
  	drop trigger simular_erro_insercao_trigger on jogador;
  
  	select totalpontosjogador(1) = 300 into resultado;
  	perform teste(12, 'Calcular total de pontos de jogador com pontos', resultado);
  	
  	select totalpontosjogador(2) = 0 into resultado;
  	perform teste(13, 'Calcular total de pontos de jogador sem pontos', resultado);
  
  	begin
    	resultado := false;
    	perform totalpontosjogador(999);
  	exception
    	when others then
      	resultado := true;
  	end;
  	perform teste(14, 'Calcular total de pontos com id de jogador inválido', resultado);
	
  	select totaljogosjogador(1) = 2 into resultado; 
  	perform teste(15, 'Verificar total de jogos de jogador com jogos', resultado);

  	select totaljogosjogador(2) = 0 into resultado;
  	perform teste(16, 'Verificar total de jogos de jogador sem jogos', resultado);

  	begin
    	resultado := false;
    	perform totaljogosjogador(999);
  	exception
    	when others then
      	resultado := true;
  	end;
  	perform teste(17, 'Verificar total de jogos com id de jogador inválido', resultado);
 
  	select total_pontos = 100 from pontosjogoporjogador('jogo002') 
  	where (jogador_id = 1 and total_pontos = 100) or (jogador_id = 2 and total_pontos = 600) into resultado; 
  	perform teste(18, 'Verificar total de pontos de jogo com jogadores', resultado);
 
  	select count(*) = 2 into resultado from pontosjogoporjogador('jogo002'); 
  	perform teste(19, 'Verificar quantos jogadores jogaram o jogo', resultado);

  	select count(*) = 0 into resultado from pontosjogoporjogador('jogo003'); 
  	perform teste(20, 'Verificar nenhum jogador jogou o jogo', resultado);
 
  	begin
    	resultado := false;
    	perform pontosjogoporjogador('jogo999');
  	exception
    	when others then
      	resultado := true;
  	end;
  	perform teste(21, 'Verificar pontos jogo por jogador com referência inválida', resultado);

  	call associarcracha(1, 'jogo001', 'bronze1');
  	select exists (
  		select 1 
  		from cracha_jogador 
  		where jogador_id = 1 and cracha_id = 1
  	) into resultado;
  	perform teste(22, 'Crachá atribuido corretamente', resultado);
  
  	begin
  		resultado := false;
  		call associarcracha(99, 'jogo001', 'bronze1');
  	exception
  		when others then
	  		select not exists (
	  			select 1 
	  			from cracha_jogador 
	  			where jogador_id = 99 and cracha_id = 1
	  		) into resultado;
  	end;
  	perform teste(23, 'Crachá não atribuido pois jogador não existe', resultado);
 
  	begin
  		resultado := false;
  		call associarcracha(1, 'jogo0011', 'bronze1');
  	exception
  		when others then
	  		select not exists (
	  			select 1 
	  			from cracha_jogador cj
	  			join cracha c on cj.cracha_id = c.id
	  			where cj.jogador_id = 1 and cracha_id = 1 and c.jogo_referencia = 'jogo0011'
	  		) into resultado;
  	end;
  	perform teste(24, 'Crachá não atribuido pois jogo não existe', resultado);
  
  	begin
  		resultado := false;
  		call associarcracha(1, 'jogo001', 'bronze11');
  	exception
  		when others then
	  		select not exists (
	  			select 1 
	  			from cracha_jogador cj
	  			join cracha c on cj.cracha_id = c.id
	  			where cj.jogador_id = 1 and cracha_id = 1 and c.nome = 'bronze11'
	  		) into resultado;
  	end;
  	perform teste(25, 'Crachá não existe', resultado);
 
  	call associarcracha(1, 'jogo001', 'prata1');
	select not exists (
		select 1 
		from cracha_jogador 
		where jogador_id = 1 and cracha_id = 2
	) into resultado;
  	perform teste(26, 'Crachá não atribuido por falta de pontos', resultado);
 
  	begin
     	resultado := false;
  	 	call associarcracha(1, 'jogo001', 'prata2');
  	exception
  	 	when others then
  			select not exists (
				select 1 
				from cracha_jogador 
				where jogador_id = 1 and cracha_id = 4
		) into resultado;
  	end;
  	perform teste(27, 'Crachá não existe no jogo', resultado);

  	create trigger simular_erro_insercao_trigger
	before insert on cracha_jogador
	for each row execute function simular_erro_insercao();

  	begin
		resultado := false;
    	call associarcracha(1, 'jogo002', 'bronze2');
  	exception
    	when others then
      		select not exists (
        		select 1
        		from cracha_jogador
        		where jogador_id = 2 and cracha_id = 2
      	) into resultado;
  	end;
  	perform teste(28, 'Transação revertida corretamente', resultado);
  	
  	drop trigger simular_erro_insercao_trigger on cracha_jogador;
  
  	delete from cracha_jogador where jogador_id = 1 and cracha_id = 1;
  	  	
  	call iniciarconversa(1, 'conversa4', v_output);
  	select exists (
  		select 1
  		from conversa
  		where id = v_output and nome = 'conversa4'
	) into resultado;
  	perform teste(29, 'Conversa criada corretamente', resultado);
  
  	select exists (
		select 1 
		from participacao_conversa 
		where jogador_id = 1 and conversa_id = v_output
	) into resultado;
  	perform teste(30, 'Jogador associado à conversa corretamente', resultado);
  
  	delete from participacao_conversa where jogador_id = 1 and conversa_id = v_output;

  	begin
		resultado := false;
  		call iniciarconversa(99, 'conversa5', v_output);
  	exception
  		when others then
  			select not exists (
  				select 1 
  				from conversa 
  				where nome = 'conversa5'
  			) into resultado;
  	end;
  	perform teste(31, 'Conversa não criada por jogador não existir', resultado);
  
  	create trigger simular_erro_insercao_trigger
	before insert on participacao_conversa
	for each row execute function simular_erro_insercao();
 
  	begin
    	resultado := false;
    	call iniciarconversa(1, 'conversa5', v_output2);
  	exception
  		when others then
  			select not exists (
  				select 1 
  				from conversa 
  				where nome = 'conversa5'
  			) and not exists (
  				select 1 
  				from participacao_conversa 
  				where jogador_id = 1 and conversa_id = v_output2
  			) into resultado;
  	end;
  	perform teste(32, 'Transação revertida corretamente', resultado);
  
  	drop trigger simular_erro_insercao_trigger on participacao_conversa;
 
  	call juntarconversa(1, 1);
  	select exists (
  		select 1 
  		from participacao_conversa
  		where jogador_id = 1 and conversa_id = 1 
  	) into resultado;
  	perform teste(33, 'Conversa juntada', resultado); 
  
  	begin
  		resultado := false;
    	call juntarconversa(99, 1);
  	exception
  		when others then
  			select not exists (
  				select 1 
  				from participacao_conversa 
  				where jogador_id = 99 and conversa_id = 1 
  			) into resultado;
  	end;
  	perform teste(34, 'Jogador não existe', resultado);
  
  	begin
  		resultado := false;
    	call juntarconversa(1, 99);
  	exception
  		when others then
  			select not exists (
  				select 1 
  				from participacao_conversa 
  				where jogador_id = 1 and conversa_id = 199
  			) into resultado;
  	end;
  	perform teste(35, 'Conversa não existe', resultado);
  
  	create trigger simular_erro_insercao_trigger
	before insert on participacao_conversa
	for each row execute function simular_erro_insercao();
 
  	begin
  		resultado := false;
    	call juntarconversa(2, 1);
  	exception
  		when others then
  			select exists (
  				select 1 
  				from participacao_conversa 
  				where jogador_id = 1 and conversa_id = 1 
  			) into resultado;
  	end;
  	perform teste(36, 'Transação revertida corretamente', resultado);
  
  	drop trigger simular_erro_insercao_trigger on participacao_conversa;
 
  	call enviarmensagem(4, 1, 'hahaaa');
  	select exists (
  		select 1 
  		from mensagem 
  		where jogador_id = 4 and conversa_id = 1 and texto = 'hahaaa'
  	) into resultado;
  	perform teste(37, 'Mensagem enviada', resultado);
  	
  	begin
  		resultado := false;
  		call enviarmensagem(99, 1, 'haha');
   	exception
  		when others then
  			select not exists (
  			select 1 
  			from mensagem
			where jogador_id = 99 and conversa_id = 1 and texto = 'haha'
		) into resultado;
   	end;
   	perform teste(38, 'Jogador não existe', resultado);
  
   	begin
  		resultado := false;
  		call enviarmensagem(1, 99, 'haha');
  	exception
  		when others then
  			select not exists (
  				select 1 
  				from mensagem
  				where jogador_id = 1 and conversa_id = 99 and texto = 'haha'
  			) into resultado;
   	end;
   	perform teste(39, 'Conversa não existe', resultado);
  
   	begin
  		resultado := false;
  		call enviarmensagem(6, 2, 'hahaa');
   	exception
  		when others then
  			select not exists (
  				select 1 
  				from mensagem 
  				where jogador_id = 6 and conversa_id = 2 and texto = 'hahaa'
  			) into resultado;
   	end;
   	perform teste(40, 'Jogador não faz parte da conversa', resultado);
  
  	create trigger simular_erro_insercao_trigger
	before insert on mensagem
	for each row execute function simular_erro_insercao();

	begin
  		resultado := false;
  		call enviarmensagem(4, 1, 'hahaa');
   	exception
  		when others then
  			select not exists (
  				select 1 
  				from mensagem 
  				where jogador_id = 4 and conversa_id = 1 and texto = 'hahaa'
  			) into resultado;
   	end;
   	perform teste(41, 'Transação revertida corretamente', resultado);

	drop trigger simular_erro_insercao_trigger on mensagem;
   
   	select count(*) = 5 into resultado 
   	from jogadortotalinfo; 
   	perform teste(42, 'Foram adicionados todos os jogadores que não estão banidos', resultado);
  
   	update partida 
   	set data_fim = '2023-02-11 12:00:00' 
   	where id = 1;
   	select count(*) = 3 into resultado 
   	from cracha_jogador 
   	where jogador_id = 1;
   	perform teste(43, 'Crachás adicionados ao jogador', resultado);
   
   	delete from cracha_jogador where jogador_id = 1 and cracha_id = 1;
   
    create trigger simular_erro_insercao_trigger
	before insert on cracha_jogador
	for each row execute function simular_erro_insercao();
   
   	begin
   		resultado := false;
   		update partida set data_fim = null where id = 1;
   		update partida set data_fim = '2023-02-11 12:00:00' where id = 1;
   	exception
   		when others then
   			select not exists (
   				select 1
   				from cracha_jogador 
   				where jogador_id = 1 and cracha_id = 1
   			) into resultado;
   	end;
    perform teste(44, 'Transação revertida corretamente', resultado);
   	
	drop trigger simular_erro_insercao_trigger on cracha_jogador;

   	delete from jogadortotalinfo 
   	where jogador_id = 1;
   	select exists (
   		select 1 
   		from jogador 
   		where id = 1 and estado = 'Banido'
   	) into resultado;
   	perform teste(45, 'Jogador banido', resultado);
   
    create trigger simular_erro_insercao_trigger
	before update on jogador
	for each row execute function simular_erro_insercao();

	begin
		resultado := false;
		delete from jogadortotalinfo 
		where jogador_id = 2;
	exception
		when others then 
			select not exists (
				select 1
				from jogador
				where id = 2 and estado = 'Banido'
			) into resultado;
	end;
	perform teste(46, 'Transação revertida corretamente', resultado);

	drop trigger simular_erro_insercao_trigger on jogador;

	begin
		rollback;
	end;
end;
$$ language plpgsql;