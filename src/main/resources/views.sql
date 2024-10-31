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

DROP VIEW IF EXISTS v_qtd_produto_por_condominio;
CREATE VIEW v_qtd_produto_por_condominio AS
SELECT
    condominio.id AS condominio_id,
    condominio.nome AS nome,
    produto_unitario.produto_id,
    COUNT(produto_unitario.id) AS qtd_produtos
FROM
    produto_unitario
        JOIN
    origem ON origem.id = produto_unitario.origem_id
        JOIN
    condominio ON condominio.id = origem.condominio_id
GROUP BY
    condominio.id, produto_unitario.produto_id
ORDER BY
    condominio.nome;

DROP VIEW IF EXISTS v_qtd_produtos_n_conforme_por_condominio;
CREATE VIEW v_qtd_produtos_n_conforme_por_condominio AS
SELECT
    condominio.id AS condominio_id,
    condominio.nome AS nome_condominio,
    COUNT(produto_unitario.conforme) AS qtd_produtos
FROM
    produto_unitario
        JOIN
    origem ON origem.id = produto_unitario.origem_id
        JOIN
    condominio ON condominio.id = origem.condominio_id
WHERE
    produto_unitario.conforme = 1
GROUP BY
    condominio.id
ORDER BY
    condominio.nome;

DROP VIEW IF EXISTS v_qtd_produtos_vencidos_por_condominio;
CREATE VIEW v_qtd_produtos_vencidos_por_condominio AS
SELECT
    condominio.id AS condominio_id,
    condominio.nome AS nome_condominio,
    COUNT(produto_unitario.id) AS qtd_vencidos
FROM
    produto_unitario
        JOIN
    origem ON origem.id = produto_unitario.origem_id
        JOIN
    condominio ON condominio.id = origem.condominio_id
WHERE
    produto_unitario.data_validade < CURDATE()
GROUP BY
    condominio.id
ORDER BY
    condominio.id;

DROP VIEW IF EXISTS v_qtd_discrepancia_condominios;
CREATE VIEW v_qtd_discrepancia_condominios AS
SELECT
    condominio.nome AS nome_condominio,
    produto_unitario.nome AS nome_produto,
    COUNT(CASE WHEN produto_unitario.conforme = 0 THEN 1 ELSE NULL END) AS qtd_conforme,
    COUNT(CASE WHEN produto_unitario.conforme = 1 THEN 1 ELSE NULL END) AS qtd_nao_conforme,
    (COUNT(CASE WHEN produto_unitario.conforme = 1 THEN 1 ELSE NULL END) -
     COUNT(CASE WHEN produto_unitario.conforme = 0 THEN 1 ELSE NULL END)) AS discrepancia
FROM
    produto_unitario
        JOIN
    origem ON origem.id = produto_unitario.origem_id
        JOIN
    condominio ON condominio.id = origem.condominio_id
GROUP BY
    condominio.id, produto_unitario.nome
ORDER BY
    discrepancia DESC
    LIMIT 4;

DROP VIEW IF EXISTS v_total_produtos_arrecadados_por_mes;
CREATE VIEW v_total_produtos_arrecadados_por_mes AS
SELECT
    DATE_FORMAT(produto_unitario.criado_em, '%Y-%m') AS mes,
    COUNT(produto_unitario.id) AS count
FROM
	produto_unitario
JOIN
	origem ON origem.id = produto_unitario.origem_id
JOIN
	condominio ON condominio.id = origem.condominio_id
GROUP BY
	DATE_FORMAT(produto_unitario.criado_em, '%Y-%m')
ORDER BY
	mes;

DROP VIEW IF EXISTS v_total_produtos_arrecadados_por_mes_condominio;
CREATE VIEW v_total_produtos_arrecadados_por_mes_condominio AS
SELECT
    condominio.id AS condominio_id,
    DATE_FORMAT(produto_unitario.criado_em, '%Y-%m') AS mes,
    COUNT(produto_unitario.id) AS count
FROM
	produto_unitario
JOIN
	origem ON origem.id = produto_unitario.origem_id
JOIN
	condominio ON condominio.id = origem.condominio_id
WHERE
	DATE_FORMAT(produto_unitario.criado_em, '%Y-%m') = (
		SELECT DATE_FORMAT(MAX(criado_em), '%Y-%m')
		FROM produto_unitario
	)
GROUP BY
	DATE_FORMAT(produto_unitario.criado_em, '%Y-%m'), condominio.id
ORDER BY
	condominio.id;

DROP VIEW IF EXISTS v_qtd_produtos_por_nome_condominio;
CREATE VIEW v_qtd_produtos_por_nome_condominio AS
SELECT
    condominio.nome AS nome_condominio,
    DATE_FORMAT(produto_unitario.criado_em, '%Y-%m') AS mes,
    COUNT(produto_unitario.id) AS count
FROM
	produto_unitario
JOIN
	origem ON origem.id = produto_unitario.origem_id
JOIN
	condominio ON condominio.id = origem.condominio_id
GROUP BY
	condominio.nome, DATE_FORMAT(produto_unitario.criado_em, '%Y-%m')
ORDER BY
	condominio.nome, mes;

DROP VIEW IF EXISTS v_qtd_total_alimentos_arrecadados_por_mes;
CREATE VIEW v_qtd_total_alimentos_arrecadados_por_mes AS
SELECT
    SUM(qtd_arrecadada) AS qtd_arrecadada,
        MONTH(data_campanha) AS mes,
        YEAR(data_campanha) AS ano
        FROM
        campanha
        GROUP BY YEAR(data_campanha) , MONTH(data_campanha)
        ORDER BY mes;

DROP VIEW IF EXISTS v_qtd_produto_por_campanha;
CREATE VIEW v_qtd_produto_por_campanha AS
SELECT
    campanha.id AS campanha_id,
    campanha.nome AS nome,
    produto_unitario.produto_id,
    COUNT(produto_unitario.id) AS qtd_produtos
FROM
    produto_unitario
        JOIN
    origem ON origem.id = produto_unitario.origem_id
        JOIN
    campanha ON campanha.id = origem.campanha_id
GROUP BY
    campanha.id, produto_unitario.produto_id
ORDER BY
    campanha.nome;

DROP VIEW IF EXISTS v_qtd_produtos_vencidos_por_campanha;
CREATE VIEW v_qtd_produtos_vencidos_por_campanha AS
SELECT
    campanha.id as campanha_id,
    campanha.nome AS nome,
    produto_unitario.produto_id,
    COUNT(produto_unitario.id) AS qtd_produtos_vencidos
FROM
    produto_unitario
        JOIN
    origem ON origem.id = produto_unitario.origem_id
        JOIN
    campanha ON campanha.id = origem.campanha_id
WHERE
    produto_unitario.vencido = TRUE
GROUP BY
    campanha.id, produto_unitario.produto_id
ORDER BY
    campanha.id;

DROP VIEW IF EXISTS v_qtd_doacoes_por_campanha;
CREATE VIEW v_qtd_doacoes_por_campanha AS
SELECT
    SUM(campanha.qtd_arrecadada) AS qtd_arrecadada,
    campanha.nome,
        MONTH(campanha.data_campanha) AS mes,
        YEAR(campanha.data_campanha) AS ano
        FROM
        campanha
        GROUP BY campanha.nome, YEAR(campanha.data_campanha), MONTH(campanha.data_campanha)
        ORDER BY nome, ano, mes;

DROP VIEW IF EXISTS v_produtos_conforme_nao_conforme_campanhas;
CREATE VIEW v_produtos_conforme_nao_conforme_campanhas AS
SELECT
    c.nome,
    SUM(CASE WHEN pu.vencido = false THEN 1 ELSE 0 END) AS conforme,
    SUM(CASE WHEN pu.vencido = true THEN 1 ELSE 0 END) AS nao_conforme
FROM
    produto_unitario pu
        JOIN produto p on pu.produto_id = p.id
        JOIN origem o on pu.origem_id = o.id
        JOIN campanha c on o.campanha_id = c.id
GROUP BY
    c.id;
