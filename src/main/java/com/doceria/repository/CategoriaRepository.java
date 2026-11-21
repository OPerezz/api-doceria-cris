package com.doceria.repository;

import com.doceria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    Optional<Categoria> findBySlug(String slug);
    
    List<Categoria> findByAtivoTrue();
    
    @Query("SELECT c FROM Categoria c WHERE c.ativo = true ORDER BY c.nome")
    List<Categoria> findAllAtivasOrdenadas();
    
    boolean existsBySlug(String slug);
}
