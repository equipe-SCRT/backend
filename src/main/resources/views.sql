
DROP TABLE IF EXISTS v_produto_unitario_qtd_ativo_por_mes;
DROP VIEW IF EXISTS v_produto_unitario_qtd_ativo_por_mes;
CREATE VIEW v_produto_unitario_qtd_ativo_por_mes AS
SELECT
    ROW_NUMBER() OVER (ORDER BY DATE(pu.criado_em)) AS id,
    DATE(pu.criado_em) AS criado_em,
    COUNT(*) AS qtd,
    pu.produto_id
FROM
    produto_unitario pu
GROUP BY
    DATE(pu.criado_em),
    pu.produto_id
ORDER BY
    DATE(pu.criado_em);

DROP TABLE IF EXISTS v_produto_unitario_qtd_vencido_por_mes;
DROP VIEW IF EXISTS v_produto_unitario_qtd_vencido_por_mes;
CREATE VIEW v_produto_unitario_qtd_vencido_por_mes AS
SELECT
    ROW_NUMBER() OVER (ORDER BY DATE(pu.data_validade)) AS id,
    DATE(pu.data_validade) AS data_validade,
    COUNT(*) AS qtd,
    pu.produto_id
FROM
    produto_unitario pu
WHERE
    pu.vencido = 1
GROUP BY
    DATE(pu.data_validade),
    pu.produto_id
ORDER BY
    DATE(pu.data_validade);

DROP TABLE IF EXISTS v_produto_unitario_vencimento_15_e_30_dias;
DROP VIEW IF EXISTS v_produto_unitario_vencimento_15_e_30_dias;
CREATE VIEW v_produto_unitario_vencimento_15_e_30_dias AS
SELECT
    (
        SELECT
            COUNT(*)
        FROM
            produto_unitario pu
        WHERE
            pu.data_validade BETWEEN CURDATE() AND CURDATE() + INTERVAL 15 DAY
    ) AS vencimento15,
    (
        SELECT
            COUNT(*)
        FROM
            produto_unitario pu
        WHERE
        pu.data_validade BETWEEN (CURDATE() + INTERVAL 16 DAY) AND (CURDATE() + INTERVAL 42 DAY)
    ) AS vencimento30;

DROP TABLE IF EXISTS v_produto_unitario_vencido_arrecadado;
DROP VIEW IF EXISTS v_produto_unitario_vencido_arrecadado;
CREATE VIEW v_produto_unitario_vencido_arrecadado AS
SELECT
    p.nome,
    SUM(CASE WHEN pu.vencido = FALSE THEN 1 ELSE 0 END) AS arrecadado,
    SUM(CASE WHEN pu.conforme = FALSE THEN 1 ELSE 0 END) AS vencido
FROM
    produto_unitario pu
        JOIN produto p ON pu.produto_id = p.id
GROUP BY
    p.id;

DROP TABLE IF EXISTS v_qtd_produto_por_condominio;
DROP VIEW IF EXISTS v_qtd_produto_por_condominio;
CREATE VIEW v_qtd_produto_por_condominio AS
SELECT
    ROW_NUMBER() OVER (ORDER BY condominio.nome) AS id,
    condominio.id AS condominio_id,
    condominio.nome AS nome,
    produto_unitario.produto_id,
    COUNT(produto_unitario.id) AS qtd_produtos
FROM
    produto_unitario
    JOIN origem ON origem.id = produto_unitario.origem_id
    JOIN condominio ON condominio.id = origem.condominio_id
GROUP BY
    condominio.id, produto_unitario.produto_id
ORDER BY
    condominio.nome;

DROP TABLE IF EXISTS v_qtd_produtos_n_conforme_por_condominio;
DROP VIEW IF EXISTS v_qtd_produtos_n_conforme_por_condominio;
CREATE VIEW v_qtd_produtos_n_conforme_por_condominio AS
SELECT
    ROW_NUMBER() OVER (ORDER BY condominio.nome) AS id,
    condominio.id AS condominio_id,
    condominio.nome AS nome_condominio,
    COUNT(produto_unitario.conforme) AS qtd_produtos
FROM
    produto_unitario
    JOIN origem ON origem.id = produto_unitario.origem_id
    JOIN condominio ON condominio.id = origem.condominio_id
WHERE
    produto_unitario.conforme = 1
GROUP BY
    condominio.id
ORDER BY
    condominio.nome;

DROP TABLE IF EXISTS v_qtd_produtos_vencidos_por_condominio;
DROP VIEW IF EXISTS v_qtd_produtos_vencidos_por_condominio;
CREATE VIEW v_qtd_produtos_vencidos_por_condominio AS
SELECT
    ROW_NUMBER() OVER (ORDER BY condominio.id) AS id,
    condominio.id AS condominio_id,
    condominio.nome AS nome_condominio,
    COUNT(produto_unitario.id) AS qtd_vencidos
FROM
    produto_unitario
    JOIN origem ON origem.id = produto_unitario.origem_id
    JOIN condominio ON condominio.id = origem.condominio_id
WHERE
    produto_unitario.data_validade < CURDATE()
GROUP BY
    condominio.id
ORDER BY
    condominio.id;

DROP TABLE IF EXISTS v_qtd_discrepancia_condominios;
DROP VIEW IF EXISTS v_qtd_discrepancia_condominios;
CREATE VIEW v_qtd_discrepancia_condominios AS
SELECT
    condominio.nome AS nome_condominio,
    produto_unitario.nome AS nome_produto,
    COUNT(CASE WHEN produto_unitario.conforme = 0 THEN 1 ELSE NULL END) AS qtd_conforme,
    COUNT(CASE WHEN produto_unitario.conforme = 1 THEN 1 ELSE NULL END) AS qtd_nao_conforme,
    COUNT(CASE WHEN produto_unitario.conforme = 0 THEN 1 ELSE NULL END) AS discrepancia
FROM
    produto_unitario
        JOIN origem ON origem.id = produto_unitario.origem_id
        JOIN condominio ON condominio.id = origem.condominio_id
GROUP BY
    condominio.id, produto_unitario.nome
ORDER BY
    discrepancia DESC
LIMIT 4;

DROP TABLE IF EXISTS v_total_produtos_arrecadados_por_mes;
DROP VIEW IF EXISTS v_total_produtos_arrecadados_por_mes;
CREATE VIEW v_total_produtos_arrecadados_por_mes AS
SELECT
    DATE(produto_unitario.criado_em) AS criado_em,
    COUNT(produto_unitario.id) AS count
FROM
    produto_unitario
        JOIN origem ON origem.id = produto_unitario.origem_id
        JOIN condominio ON condominio.id = origem.condominio_id
GROUP BY
    DATE(produto_unitario.criado_em)
ORDER BY
    criado_em;

DROP TABLE IF EXISTS v_total_produtos_arrecadados_por_mes_condominio;
DROP VIEW IF EXISTS v_total_produtos_arrecadados_por_mes_condominio;
CREATE VIEW v_total_produtos_arrecadados_por_mes_condominio AS
SELECT
    condominio.id AS condominio_id,
    DATE(produto_unitario.criado_em) AS criado_em,
    COUNT(produto_unitario.id) AS count
FROM
    produto_unitario
        JOIN origem ON origem.id = produto_unitario.origem_id
        JOIN condominio ON condominio.id = origem.condominio_id
WHERE
    DATE(produto_unitario.criado_em) = (
        SELECT DATE(MAX(criado_em))
        FROM produto_unitario
    )
GROUP BY
    DATE(produto_unitario.criado_em), condominio.id
ORDER BY
    condominio.id;

DROP TABLE IF EXISTS v_qtd_produtos_por_nome_condominio;
DROP VIEW IF EXISTS v_qtd_produtos_por_nome_condominio;
CREATE VIEW v_qtd_produtos_por_nome_condominio AS
SELECT
    ROW_NUMBER() OVER (ORDER BY condominio.nome, DATE(produto_unitario.criado_em)) AS id,
    condominio.nome AS nome_condominio,
    DATE(produto_unitario.criado_em) AS criado_em,
    COUNT(produto_unitario.id) AS count
FROM
    produto_unitario
    JOIN origem ON origem.id = produto_unitario.origem_id
    JOIN condominio ON condominio.id = origem.condominio_id
GROUP BY
    condominio.nome, DATE(produto_unitario.criado_em)
ORDER BY
    condominio.nome, criado_em;

DROP TABLE IF EXISTS v_qtd_total_alimentos_arrecadados_por_mes;
DROP VIEW IF EXISTS v_qtd_total_alimentos_arrecadados_por_mes;
CREATE VIEW v_qtd_total_alimentos_arrecadados_por_mes AS
SELECT
    ROW_NUMBER() OVER (ORDER BY DATE(data_campanha)) AS id,
    SUM(qtd_arrecadada) AS qtd_arrecadada,
    DATE(data_campanha) AS data_campanha
FROM
    campanha
GROUP BY DATE(data_campanha)
ORDER BY data_campanha;

DROP TABLE IF EXISTS v_qtd_produto_por_campanha;
DROP VIEW IF EXISTS v_qtd_produto_por_campanha;
CREATE VIEW v_qtd_produto_por_campanha AS
SELECT
    ROW_NUMBER() OVER (ORDER BY campanha.local_campanha) AS id,
    campanha.id AS campanha_id,
    campanha.local_campanha AS nome,
    produto_unitario.produto_id,
    COUNT(produto_unitario.id) AS qtd_produtos
FROM
    produto_unitario
    JOIN origem ON origem.id = produto_unitario.origem_id
    JOIN campanha ON campanha.id = origem.campanha_id
GROUP BY
    campanha.id, produto_unitario.produto_id
ORDER BY
    campanha.local_campanha;

DROP TABLE IF EXISTS v_qtd_produtos_vencidos_por_campanha;
DROP VIEW IF EXISTS v_qtd_produtos_vencidos_por_campanha;
CREATE VIEW v_qtd_produtos_vencidos_por_campanha AS
SELECT
    ROW_NUMBER() OVER (ORDER BY campanha.id) AS id,
    campanha.id AS campanha_id,
    campanha.local_campanha AS nome,
    produto_unitario.produto_id,
    COUNT(produto_unitario.id) AS qtd_produtos_vencidos
FROM
    produto_unitario
    JOIN origem ON origem.id = produto_unitario.origem_id
    JOIN campanha ON campanha.id = origem.campanha_id
WHERE
    produto_unitario.vencido = TRUE
GROUP BY
    campanha.id, produto_unitario.produto_id
ORDER BY
    campanha.id;

DROP TABLE IF EXISTS v_qtd_doacoes_por_campanha;
DROP VIEW IF EXISTS v_qtd_doacoes_por_campanha;
CREATE VIEW v_qtd_doacoes_por_campanha AS
SELECT
    ROW_NUMBER() OVER (ORDER BY campanha.local_campanha, DATE(campanha.data_campanha)) AS id,
    SUM(campanha.qtd_arrecadada) AS qtd_arrecadada,
    campanha.local_campanha AS nome,
    DATE(campanha.data_campanha) AS data_campanha
FROM
    campanha
GROUP BY campanha.local_campanha, DATE(campanha.data_campanha)
ORDER BY nome, data_campanha;

DROP TABLE IF EXISTS v_produtos_conforme_nao_conforme_campanhas;
DROP VIEW IF EXISTS v_produtos_conforme_nao_conforme_campanhas;
CREATE VIEW v_produtos_conforme_nao_conforme_campanhas AS
SELECT
    c.local_campanha AS nome,
    SUM(CASE WHEN pu.vencido = FALSE THEN 1 ELSE 0 END) AS conforme,
    SUM(CASE WHEN pu.vencido = TRUE THEN 1 ELSE 0 END) AS nao_conforme
FROM
    produto_unitario pu
        JOIN produto p ON pu.produto_id = p.id
        JOIN origem o ON pu.origem_id = o.id
        JOIN campanha c ON o.campanha_id = c.id
GROUP BY
    c.id;
