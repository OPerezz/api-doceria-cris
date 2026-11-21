package com.doceria.repository;

import com.doceria.model.Avaliacao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
    List<Avaliacao> findByProdutoId(Long produtoId);
    
    @Query("SELECT AVG(a.nota) FROM Avaliacao a WHERE a.produto.id = :produtoId")
    Double calcularMediaNotaPorProduto(@Param("produtoId") Long produtoId);
    
    @Query("SELECT COUNT(a) FROM Avaliacao a WHERE a.produto.id = :produtoId")
    Long countAvaliacoesPorProduto(@Param("produtoId") Long produtoId);
    
    @Query("SELECT a FROM Avaliacao a WHERE a.produto.id = :produtoId ORDER BY a.data DESC")
    List<Avaliacao> findUltimasAvaliacoesPorProduto(@Param("produtoId") Long produtoId, Pageable pageable);
}
