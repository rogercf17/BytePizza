-- Categorias
INSERT INTO categoria (nome, ordem) VALUES
    ('Tradicionais', 1),
    ('Especiais',    2),
    ('Premium',      3),
    ('Doces',        4);

-- Tradicionais
INSERT INTO sabor (categoria_id, nome, descricao, preco, vegetariano)
SELECT c.id, v.nome, v.descricao, v.preco, v.vegetariano
FROM categoria c
JOIN (VALUES
    ('Mussarela',  'Molho de tomate, mussarela, azeitona, orégano',          39.90, TRUE),
    ('Calabresa',  'Calabresa fatiada, cebola, mussarela, orégano',          42.90, FALSE),
    ('Napolitana', 'Mussarela, tomate fatiado, parmesão, orégano',           42.90, TRUE),
    ('Presunto',   'Presunto, mussarela, azeitona',                          42.90, FALSE),
    ('Margherita', 'Mussarela, tomate, manjericão fresco, parmesão',         44.90, TRUE),
    ('Portuguesa', 'Presunto, ovo, cebola, ervilha, mussarela, azeitona',    46.90, FALSE)
) AS v (nome, descricao, preco, vegetariano) ON TRUE
WHERE c.nome = 'Tradicionais';

-- Especiais
INSERT INTO sabor (categoria_id, nome, descricao, preco, vegetariano)
SELECT c.id, v.nome, v.descricao, v.preco, v.vegetariano
FROM categoria c
JOIN (VALUES
    ('Baiana',               'Calabresa moída, pimenta, cebola, ovo, mussarela',         49.90, FALSE),
    ('Frango com Catupiry',  'Frango desfiado, requeijão cremoso, milho',                52.90, FALSE),
    ('Bacon',                'Bacon crocante, mussarela, cebola',                        52.90, FALSE),
    ('Quatro Queijos',       'Mussarela, provolone, parmesão, requeijão cremoso',        54.90, TRUE),
    ('Atum',                 'Atum, cebola, mussarela, azeitona',                        54.90, FALSE),
    ('Lombo Canadense',      'Lombo defumado, mussarela, cebola caramelizada',           56.90, FALSE),
    ('Strogonoff de Frango', 'Strogonoff de frango, mussarela, batata palha',            58.90, FALSE)
) AS v (nome, descricao, preco, vegetariano) ON TRUE
WHERE c.nome = 'Especiais';

-- Premium
INSERT INTO sabor (categoria_id, nome, descricao, preco, vegetariano)
SELECT c.id, v.nome, v.descricao, v.preco, v.vegetariano
FROM categoria c
JOIN (VALUES
    ('Carne Seca com Catupiry', 'Carne seca desfiada, requeijão cremoso, cebola roxa',  66.90, FALSE),
    ('Filé Mignon com Cheddar', 'Tiras de filé, cheddar, cebola crocante',              69.90, FALSE),
    ('Parma com Rúcula',        'Presunto cru, rúcula, tomate seco, mussarela',         72.90, FALSE),
    ('Camarão com Catupiry',    'Camarão refogado, requeijão cremoso, alho-poró',       74.90, FALSE)
) AS v (nome, descricao, preco, vegetariano) ON TRUE
WHERE c.nome = 'Premium';

-- Doces
INSERT INTO sabor (categoria_id, nome, descricao, preco, vegetariano)
SELECT c.id, v.nome, v.descricao, v.preco, v.vegetariano
FROM categoria c
JOIN (VALUES
    ('Banana com Canela',    'Banana, canela, açúcar, leite condensado', 46.90, TRUE),
    ('Romeu e Julieta',      'Queijo minas, goiabada',                   49.90, TRUE),
    ('Chocolate com Morango','Chocolate ao leite, morango',              52.90, TRUE)
) AS v (nome, descricao, preco, vegetariano) ON TRUE
WHERE c.nome = 'Doces';

-- Configuração da loja (valores fictícios; ajuste a coordenada para a sua cidade)
INSERT INTO configuracao_loja (
    id, pedido_minimo, loja_pausada, validade_pix_minutos, mensagem_aviso,
    latitude_loja, longitude_loja,
    taxa_base, raio_taxa_base_km, taxa_por_km_excedente,
    prazo_min_minutos, prazo_max_minutos
) VALUES (
    1, 30.00, FALSE, 15, 'Projeto fictício. Não use dados reais.',
    -23.550500, -46.633300,
    7.00, 5, 2.00,
    30, 45
);

-- Horário de funcionamento: todos os dias, 18:00 às 23:30 (fictício)
INSERT INTO horario_funcionamento (dia_semana, abre, fecha)
SELECT d, TIME '18:00', TIME '23:30'
FROM generate_series(0, 6) AS d;