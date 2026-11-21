package com.doceria.controller;

import com.doceria.dto.CategoriaDTO;
import com.doceria.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
@Tag(name = "Categorias", description = "API para gerenciamento de categorias da doceria")
public class CategoriaController {
    
    private final CategoriaService categoriaService;
    
    @GetMapping
    @Operation(summary = "Listar todas as categorias")
    public ResponseEntity<List<CategoriaDTO>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }
    
    @GetMapping("/ativas")
    @Operation(summary = "Listar apenas categorias ativas")
    public ResponseEntity<List<CategoriaDTO>> listarAtivas() {
        return ResponseEntity.ok(categoriaService.listarAtivas());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por ID")
    public ResponseEntity<CategoriaDTO> buscarPorId(
            @Parameter(description = "ID da categoria") @PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }
    
    @GetMapping("/slug/{slug}")
    @Operation(summary = "Buscar categoria por slug")
    public ResponseEntity<CategoriaDTO> buscarPorSlug(
            @Parameter(description = "Slug da categoria") @PathVariable String slug) {
        return ResponseEntity.ok(categoriaService.buscarPorSlug(slug));
    }
    
    @PostMapping
    @Operation(summary = "Criar uma nova categoria")
    public ResponseEntity<CategoriaDTO> criar(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO created = categoriaService.criar(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma categoria existente")
    public ResponseEntity<CategoriaDTO> atualizar(
            @Parameter(description = "ID da categoria") @PathVariable Long id,
            @Valid @RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.ok(categoriaService.atualizar(id, categoriaDTO));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma categoria")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID da categoria") @PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
