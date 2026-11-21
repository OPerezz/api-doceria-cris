-- ========================================
-- INSERÇÃO DOS DADOS INICIAIS
-- ========================================

-- Inserir Categorias
INSERT INTO categorias (id, nome, slug, descricao, icone, ativo) VALUES
(1, 'Bolos', 'bolos', 'Bolos artesanais feitos com ingredientes selecionados', '🎂', true),
(2, 'Tortas', 'tortas', 'Tortas doces e salgadas com massa crocante', '🥧', true),
(3, 'Doces Finos', 'doces-finos', 'Doces gourmet para ocasiões especiais', '🍫', true),
(4, 'Pães', 'paes', 'Pães artesanais fermentados naturalmente', '🍞', true),
(5, 'Brigadeiros', 'brigadeiros', 'Brigadeiros gourmet com sabores exclusivos', '🍬', true),
(6, 'Cupcakes', 'cupcakes', 'Cupcakes decorados com muito carinho', '🧁', true),
(7, 'Trufas', 'trufas', 'Trufas de chocolate belga e outros sabores', '🍫', true),
(8, 'Bolos de Pote', 'bolos-de-pote', 'Bolos individuais em potinhos práticos', '🫙', true),
(9, 'Biscoitos', 'biscoitos', 'Biscoitos amanteigados e crocantes', '🍪', true),
(10, 'Bebidas', 'bebidas', 'Bebidas quentes e geladas para acompanhar', '☕', true);

-- Inserir Produtos
INSERT INTO produtos (id, nome, descricao, preco, preco_promocional, tempo_preparo, categoria_id, ingredientes, destaque, disponivel, peso, imagem, avaliacao, total_vendidos) VALUES
-- Bolos
(1, 'Bolo de Chocolate Belga', 'Bolo de chocolate 70% cacau com recheio de ganache e cobertura de chocolate belga', 89.90, NULL, 90, 1, 'Farinha de trigo, Chocolate belga 70%, Manteiga, Ovos, Açúcar, Creme de leite, Cacau em pó', true, true, '1.2kg', 'bolo-chocolate-belga.jpg', 4.9, 156),
(2, 'Bolo de Cenoura com Cobertura de Chocolate', 'Bolo fofinho de cenoura com cobertura de chocolate meio amargo e raspas de laranja', 69.90, 59.90, 75, 1, 'Cenoura, Farinha de trigo, Chocolate meio amargo, Ovos, Óleo, Açúcar, Fermento', true, true, '1.0kg', 'bolo-cenoura.jpg', 4.8, 203),
(3, 'Bolo de Frutas Vermelhas', 'Bolo branco com recheio de geleia de frutas vermelhas e cobertura de cream cheese', 94.90, NULL, 100, 1, 'Farinha de trigo, Frutas vermelhas (morango, framboesa, amora), Cream cheese, Manteiga, Ovos, Açúcar, Leite', true, true, '1.3kg', 'bolo-frutas-vermelhas.jpg', 4.9, 89),
(4, 'Bolo de Coco com Calda de Abacaxi', 'Bolo úmido de coco com calda de abacaxi e coco ralado', 74.90, 64.90, 80, 1, 'Coco ralado, Farinha de trigo, Abacaxi, Leite condensado, Manteiga, Ovos, Açúcar', false, true, '1.1kg', 'bolo-coco-abacaxi.jpg', 4.7, 67),
(5, 'Bolo Red Velvet', 'Bolo vermelho aveludado com cream cheese e cobertura de chocolate branco', 99.90, NULL, 110, 1, 'Farinha de trigo, Cream cheese, Chocolate branco, Manteiga, Ovos, Açúcar, Corante vermelho, Leite', true, true, '1.4kg', 'bolo-red-velvet.jpg', 4.9, 134),

-- Tortas
(6, 'Torta de Limão com Merengue', 'Torta de limão siciliano com merengue italiano e massa amanteigada', 59.90, NULL, 60, 2, 'Limão siciliano, Manteiga, Farinha de trigo, Açúcar, Ovos, Claras, Creme de leite', true, true, '800g', 'torta-limao.jpg', 4.9, 178),
(7, 'Torta de Morango com Chocolate', 'Torta com base de chocolate, recheio de mousse de morango e cobertura de chocolate', 64.90, 54.90, 70, 2, 'Morango, Chocolate meio amargo, Creme de leite, Manteiga, Farinha de trigo, Açúcar, Gelatina', false, true, '850g', 'torta-morango.jpg', 4.8, 92),
(8, 'Torta de Nozes com Caramelo', 'Torta com recheio de nozes caramelizadas e cobertura de caramelo salgado', 69.90, NULL, 65, 2, 'Nozes, Caramelo, Manteiga, Farinha de trigo, Açúcar mascavo, Ovos, Creme de leite', true, true, '900g', 'torta-nozes.jpg', 4.7, 56),

-- Doces Finos
(9, 'Cestinha de Morango com Chocolate', 'Cestinha de massa folhada com creme de chocolate e morangos frescos', 18.90, NULL, 25, 3, 'Massa folhada, Chocolate branco, Morango, Creme de leite, Açúcar', true, true, '120g', 'cestinha-morango.jpg', 4.9, 234),
(10, 'Macaron de Framboesa', 'Macarons franceses com recheio de ganache de framboesa', 8.90, NULL, 45, 3, 'Amêndoas, Açúcar, Claras, Framboesa, Chocolate branco, Creme de leite', true, true, '40g', 'macaron-framboesa.jpg', 4.9, 312),
(11, 'Éclair de Chocolate', 'Éclair de massa choux com creme de chocolate belga e cobertura de glacê', 12.90, 10.90, 35, 3, 'Farinha de trigo, Manteiga, Ovos, Chocolate belga, Creme de leite, Açúcar', false, true, '80g', 'eclair-chocolate.jpg', 4.8, 167),

-- Pães
(12, 'Pão Artesanal de Fermentação Natural', 'Pão de fermentação natural com crosta crocante e miolo macio', 24.90, NULL, 180, 4, 'Farinha de trigo, Água, Sal, Fermento natural, Farinha de centeio', true, true, '400g', 'pao-fermentacao.jpg', 4.9, 89),
(13, 'Pão de Mel com Recheio de Doce de Leite', 'Pão de mel macio com recheio de doce de leite e cobertura de chocolate', 15.90, NULL, 50, 4, 'Farinha de trigo, Mel, Doce de leite, Chocolate, Canela, Cravinho, Leite', true, true, '150g', 'pao-de-mel.jpg', 4.9, 245),

-- Brigadeiros
(14, 'Brigadeiro Tradicional', 'Brigadeiro gourmet feito com chocolate 50% cacau e granulado de chocolate', 3.90, NULL, 20, 5, 'Leite condensado, Chocolate 50%, Manteiga, Granulado de chocolate', true, true, '30g', 'brigadeiro-tradicional.jpg', 4.9, 456),
(15, 'Brigadeiro de Ninho com Nutella', 'Brigadeiro de leite Ninho recheado com Nutella e finalizado com leite em pó', 4.90, NULL, 25, 5, 'Leite condensado, Leite Ninho, Nutella, Manteiga, Leite em pó', true, true, '35g', 'brigadeiro-ninho.jpg', 4.9, 389),
(16, 'Brigadeiro de Pistache', 'Brigadeiro de pistache com pedaços da fruta e cobertura de chocolate branco', 5.90, NULL, 30, 5, 'Leite condensado, Pistache, Chocolate branco, Manteiga, Creme de leite', true, true, '35g', 'brigadeiro-pistache.jpg', 4.8, 145),

-- Cupcakes
(17, 'Cupcake de Chocolate com Nutella', 'Cupcake de chocolate com recheio de Nutella e cobertura de buttercream de chocolate', 12.90, NULL, 30, 6, 'Farinha de trigo, Chocolate, Nutella, Manteiga, Ovos, Açúcar, Leite', true, true, '100g', 'cupcake-nutella.jpg', 4.9, 223),
(18, 'Cupcake de Baunilha com Morango', 'Cupcake de baunilha com pedaços de morango e cobertura de cream cheese', 11.90, NULL, 28, 6, 'Farinha de trigo, Baunilha, Morango, Cream cheese, Manteiga, Ovos, Açúcar', false, true, '95g', 'cupcake-morango.jpg', 4.7, 167),

-- Trufas
(19, 'Trufa de Chocolate Amargo 70%', 'Trufa com chocolate 70% cacau e recheio de ganache de café', 7.90, NULL, 30, 7, 'Chocolate 70%, Creme de leite, Café, Manteiga, Cacau em pó', true, true, '35g', 'trufa-amargo.jpg', 4.9, 278),
(20, 'Trufa de Maracujá com Chocolate Branco', 'Trufa de chocolate branco com recheio de maracujá e cobertura de raspas', 8.90, NULL, 35, 7, 'Chocolate branco, Maracujá, Creme de leite, Manteiga, Açúcar', false, true, '35g', 'trufa-maracuja.jpg', 4.8, 156),

-- Bolos de Pote
(21, 'Bolo de Pote - Churros', 'Camadas de bolo de canela, doce de leite e cobertura de chocolate com granulado', 14.90, NULL, 25, 8, 'Farinha de trigo, Doce de leite, Chocolate, Canela, Manteiga, Ovos, Leite', true, true, '200g', 'bolo-pote-churros.jpg', 4.9, 198),
(22, 'Bolo de Pote - Sensação', 'Camadas de bolo de chocolate, brigadeiro e morangos frescos', 15.90, 13.90, 25, 8, 'Farinha de trigo, Chocolate, Brigadeiro, Morango, Manteiga, Ovos, Leite', true, true, '200g', 'bolo-pote-sensacao.jpg', 4.9, 234),

-- Biscoitos
(23, 'Biscoito Amanteigado de Chocolate', 'Biscoito amanteigado com gotas de chocolate belga', 8.90, NULL, 20, 9, 'Farinha de trigo, Chocolate belga, Manteiga, Açúcar, Ovos, Baunilha', true, true, '150g', 'biscoito-chocolate.jpg', 4.8, 312),
(24, 'Biscoito de Gengibre e Canela', 'Biscoito crocante com gengibre e canela, ideal para o chá', 9.90, NULL, 22, 9, 'Farinha de trigo, Gengibre, Canela, Manteiga, Melado, Ovos', false, true, '150g', 'biscoito-gengibre.jpg', 4.7, 89),

-- Bebidas
(25, 'Café Especial da Casa', 'Café coado na hora com grãos especiais da região sul de Minas', 8.90, NULL, 5, 10, 'Café especial, Água filtrada, Açúcar (opcional)', true, true, '200ml', 'cafe-casa.jpg', 4.9, 423),
(26, 'Chocolate Quente Especial', 'Chocolate quente com chocolate belga derretido e marshmallows', 12.90, NULL, 5, 10, 'Chocolate belga, Leite, Creme de leite, Marshmallow', true, true, '300ml', 'chocolate-quente.jpg', 4.9, 289),
(27, 'Suco Detox de Frutas Vermelhas', 'Suco natural de frutas vermelhas com hortelã e gengibre', 10.90, NULL, 5, 10, 'Morango, Framboesa, Mirtilo, Hortelã, Gengibre, Água de coco', false, true, '400ml', 'suco-detox.jpg', 4.6, 78);

-- Inserir Avaliações
INSERT INTO avaliacoes (produto_id, usuario, nota, comentario, data) VALUES
(1, 'Ana Silva', 5, 'O melhor bolo de chocolate que já comi!', '2026-08-20'),
(2, 'Carlos Santos', 4, 'Bolo muito fofinho, mas achei a cobertura muito doce', '2026-08-19'),
(6, 'Mariana Oliveira', 5, 'Torta de limão perfeita! Merengue no ponto certo', '2026-08-18'),
(14, 'João Pereira', 5, 'Melhor brigadeiro da cidade!', '2026-08-17'),
(1, 'Fernanda Lima', 5, 'Entrega rápida e bolo delicioso!', '2026-08-16'),
(17, 'Ricardo Souza', 4, 'Cupcake muito bom, mas poderia ter mais recheio', '2026-08-15'),
(25, 'Patrícia Silva', 5, 'Café excelente, recomendo!', '2026-08-14');