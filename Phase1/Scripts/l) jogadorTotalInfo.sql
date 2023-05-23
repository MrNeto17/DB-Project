drop view if exists jogadorTotalInfo;

create view jogadorTotalInfo as
select 
    j.id as jogador_id,
    j.estado,
    j.email,
    j.username,
    count(distinct p.jogo_referencia) as numero_total_de_jogos,
    count(distinct pn.partida_id) + count(distinct pm.partida_id) as numero_total_de_partidas,
    sum(case when pn.pontuacao is null then 0 else pn.pontuacao end) +
    sum(case when pa.pontuacao is null then 0 else pa.pontuacao end) as numero_total_de_pontos
from jogador j
    left join partida_normal pn on j.id = pn.jogador_id
    left join participacao pa on j.id = pa.jogador_id
    left join partida_multi_jogador pm on pa.partida_multi_id = pm.partida_id
    left join partida p on (p.id = pn.partida_id or p.id = pm.partida_id)
where j.estado != 'Banido'
group by j.id;
