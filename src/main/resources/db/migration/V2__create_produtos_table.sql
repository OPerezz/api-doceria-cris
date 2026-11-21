-- ========================================
-- CRIAÇÃO DA TABELA PRODUTOS
-- ========================================

CREATE TABLE IF NOT EXISTS produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    preco_promocional DECIMAL(10, 2),
    tempo_preparo INT NOT NULL, -- em minutos
    categoria_id BIGINT NOT NULL,
    ingredientes TEXT,
    destaque BOOLEAN DEFAULT FALSE,
    disponivel BOOLEAN DEFAULT TRUE,
    peso VARCHAR(50),
    imagem VARCHAR(255),
    avaliacao DECIMAL(3, 2) DEFAULT 0.00,
    total_vendidos INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_produtos_categoria FOREIGN KEY (categoria_id) 
        REFERENCES categorias(id) ON DELETE RESTRICT,
    CONSTRAINT chk_produtos_preco CHECK (preco >= 0),
    CONSTRAINT chk_produtos_tempo_preparo CHECK (tempo_preparo >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_produtos_categoria ON produtos(categoria_id);
CREATE INDEX idx_produtos_destaque ON produtos(destaque);
CREATE INDEX idx_produtos_disponivel ON produtos(disponivel);
CREATE INDEX idx_produtos_preco ON produtos(preco);