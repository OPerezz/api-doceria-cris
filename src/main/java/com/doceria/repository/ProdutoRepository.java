package com.doceria.repository;

import com.doceria.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    // Buscas básicas
    List<Produto> findByCategoriaId(Long categoriaId);
    
    List<Produto> findByDestaqueTrue();
    
    List<Produto> findByDisponivelTrue();
    
    Optional<Produto> findByNome(String nome);
    
    // Buscas com paginação
    Page<Produto> findByDisponivelTrue(Pageable pageable);
    
    Page<Produto> findByCategoriaIdAndDisponivelTrue(Long categoriaId, Pageable pageable);
    
    // Buscas com JPQL
    @Query("SELECT p FROM Produto p WHERE " +
           "(:termo IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(p.descricao) LIKE LOWER(CONCAT('%', :termo, '%'))) " +
           "AND (:categoriaId IS NULL OR p.categoria.id = :categoriaId) " +
           "AND (:minPreco IS NULL OR p.preco >= :minPreco) " +
           "AND (:maxPreco IS NULL OR p.preco <= :maxPreco) " +
           "AND (:apenasDestaque IS NULL OR :apenasDestaque = false OR p.destaque = true) " +
           "AND p.disponivel = true")
    Page<Produto> buscarAvancada(
            @Param("termo") String termo,
            @Param("categoriaId") Long categoriaId,
            @Param("minPreco") BigDecimal minPreco,
            @Param("maxPreco") BigDecimal maxPreco,
            @Param("apenasDestaque") Boolean apenasDestaque,
            Pageable pageable
    );
    
    // Relatórios
    @Query("SELECT COUNT(p) FROM Produto p WHERE p.precoPromocional IS NOT NULL")
    Long countProdutosComDesconto();
    
    @Query("SELECT AVG(p.avaliacao) FROM Produto p")
    Double getMediaAvaliacoes();
    
    @Query("SELECT p FROM Produto p ORDER BY p.totalVendidos DESC")
    List<Produto> findMaisVendidos(Pageable pageable);
    
    @Query("SELECT SUM(p.preco) FROM Produto p")
    BigDecimal sumPrecoTotal();
    
    @Query("SELECT AVG(p.preco) FROM Produto p")
    BigDecimal avgPrecoMedio();
}
