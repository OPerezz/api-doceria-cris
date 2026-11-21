package com.doceria.controller;

import com.doceria.dto.AvaliacaoDTO;
import com.doceria.service.AvaliacaoService;
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
@RequestMapping("/api/v1/avaliacoes")
@RequiredArgsConstructor
@Tag(name = "Avaliações", description = "API para gerenciamento de avaliações dos produtos")
public class AvaliacaoController {
    
    private final AvaliacaoService avaliacaoService;
    
    @GetMapping("/produto/{produtoId}")
    @Operation(summary = "Listar avaliações de um produto")
    public ResponseEntity<List<AvaliacaoDTO>> listarPorProduto(
            @Parameter(description = "ID do produto") @PathVariable Long produtoId) {
        return ResponseEntity.ok(avaliacaoService.listarPorProduto(produtoId));
    }
    
    @GetMapping("/produto/{produtoId}/ultimas")
    @Operation(summary = "Listar últimas avaliações de um produto")
    public ResponseEntity<List<AvaliacaoDTO>> listarUltimasPorProduto(
            @Parameter(description = "ID do produto") @PathVariable Long produtoId,
            @Parameter(description = "Quantidade máxima") @RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(avaliacaoService.listarUltimasPorProduto(produtoId, limit));
    }
    
    @PostMapping
    @Operation(summary = "Criar uma nova avaliação")
    public ResponseEntity<AvaliacaoDTO> criar(@Valid @RequestBody AvaliacaoDTO avaliacaoDTO) {
        AvaliacaoDTO created = avaliacaoService.criar(avaliacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma avaliação")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID da avaliação") @PathVariable Long id) {
        avaliacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
