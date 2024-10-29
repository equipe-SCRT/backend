DROP TABLE IF EXISTS tech_for_good.v_produto_unitario_qtd_ativo_por_mes;
CREATE VIEW IF NOT EXISTS tech_for_good.v_produto_unitario_qtd_ativo_por_mes AS
select
        date(pu.criado_em) as 'criado_em',
        COUNT(*) as 'qtd',
        pu.produto_id
        from
        tech_for_good.produto_unitario pu
        group by
        date(pu.criado_em),
        pu.produto_id
        order by
        pu.criado_em;

DROP TABLE IF EXISTS tech_for_good.v_produto_unitario_qtd_vencido_por_mes;
CREATE VIEW IF NOT EXISTS tech_for_good.v_produto_unitario_qtd_vencido_por_mes AS
select
        date(pu.data_validade) as 'data_validade',
        COUNT(*) as 'qtd',
        pu.produto_id
        from
        tech_for_good.produto_unitario pu
        where
        pu.vencido = 1
        group by
        date(pu.data_validade),
        pu.produto_id
        order by
        pu.data_validade;

DROP TABLE IF EXISTS tech_for_good.v_produto_unitario_vencimento_15_e_30_dias;
CREATE VIEW IF NOT EXISTS tech_for_good.v_produto_unitario_vencimento_15_e_30_dias AS
select
    (
        select
            count(*)
        from
            tech_for_good.produto_unitario pu
        where
            pu.data_validade between curdate() and curdate() + interval 15 day) AS vencimento15,
        (
        select
        count(*)
        from
        tech_for_good.produto_unitario pu
        where
        pu.data_validade > curdate() + interval 16 day) AS vencimento30;

DROP TABLE IF EXISTS tech_for_good.v_produto_unitario_vencido_arrecadado ;
CREATE VIEW IF NOT EXISTS tech_for_good.v_produto_unitario_vencido_arrecadado  AS SELECT
        p.nome,
        SUM(CASE WHEN pu.vencido = false THEN 1 ELSE 0 END) AS arrecadado,
        SUM(CASE WHEN pu.vencido = true THEN 1 ELSE 0 END) AS vencido
    FROM
        tech_for_good.produto_unitario pu
            JOIN tech_for_good.produto p on pu.produto_id = p.id
    GROUP BY
        p.id;
