-- ========================================
-- CRIAÇÃO DA TABELA AVALIACOES
-- ========================================

CREATE TABLE IF NOT EXISTS avaliacoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    produto_id BIGINT NOT NULL,
    usuario VARCHAR(100) NOT NULL,
    nota INT NOT NULL,
    comentario TEXT,
    data DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_avaliacoes_produto FOREIGN KEY (produto_id) 
        REFERENCES produtos(id) ON DELETE CASCADE,
    CONSTRAINT chk_avaliacoes_nota CHECK (nota BETWEEN 1 AND 5)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_avaliacoes_produto ON avaliacoes(produto_id);
CREATE INDEX idx_avaliacoes_nota ON avaliacoes(nota);