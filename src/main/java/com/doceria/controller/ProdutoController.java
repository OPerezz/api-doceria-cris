package com.doceria.controller;

import com.doceria.dto.ProdutoDTO;
import com.doceria.dto.ProdutoResponseDTO;
import com.doceria.dto.RelatorioDTO;
import com.doceria.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/produtos")
@RequiredArgsConstructor
@Tag(name = "Produtos", description = "API para gerenciamento de produtos da doceria")
public class ProdutoController {

    private final ProdutoService produtoService;

    private final Set<String> CHAVES_VALIDAS
    = Set.of(
    		"cco123",
    		"azul123");

    
    @GetMapping("/public")
    @Operation(summary = "Listar todos os produtos")
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }
    
    @GetMapping("/public/paginado")
    @Operation(summary = "Listar produtos com paginação")
    public ResponseEntity<Page<ProdutoResponseDTO>> listarPaginado(
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(produtoService.listarPaginado(pageable));
    }
    
    @GetMapping("/public/{id}")
    @Operation(summary = "Buscar produto por ID")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(
            @Parameter(description = "ID do produto") @PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }
    
    @GetMapping("/public/categoria/{categoriaId}")
    @Operation(summary = "Listar produtos por categoria")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarPorCategoria(
            @Parameter(description = "ID da categoria") @PathVariable Long categoriaId) {
        return ResponseEntity.ok(produtoService.buscarPorCategoria(categoriaId));
    }
    
    @GetMapping("/public/destaques")
    @Operation(summary = "Listar produtos em destaque")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarDestaques() {
        return ResponseEntity.ok(produtoService.buscarDestaques());
    }
    
    @GetMapping("/public/busca")
    @Operation(summary = "Busca avançada de produtos")
    public ResponseEntity<Page<ProdutoResponseDTO>> buscaAvancada(
            @Parameter(description = "Termo de busca") @RequestParam(required = false) String termo,
            @Parameter(description = "ID da categoria") @RequestParam(required = false) Long categoriaId,
            @Parameter(description = "Preço mínimo") @RequestParam(required = false) BigDecimal minPreco,
            @Parameter(description = "Preço máximo") @RequestParam(required = false) BigDecimal maxPreco,
            @Parameter(description = "Apenas destaque") @RequestParam(required = false) Boolean apenasDestaque,
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        
        return ResponseEntity.ok(produtoService.buscarAvancada(
                termo, categoriaId, minPreco, maxPreco, apenasDestaque, pageable));
    }
    
    @PostMapping
    @Operation(summary = "Criar um novo produto")
    public ResponseEntity<?>criar
    (@RequestHeader("X-API-KEY") String apiKey,
     @Valid @RequestBody ProdutoDTO produtoDTO){


   // public ResponseEntity<ProdutoResponseDTO> criar(@Valid @RequestBody ProdutoDTO produtoDTO) {

    //    if (!CHAVES_VALIDAS.contains(apiKey)) {
    //		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    //				.body("{\"erro\":\"Chave API inválida\"}");
    //	}
 //
        ProdutoResponseDTO created = produtoService.criar(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um produto existente")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @Parameter(description = "ID do produto") @PathVariable Long id,
            @Valid @RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(produtoService.atualizar(id, produtoDTO));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um produto")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do produto") @PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/disponibilidade")
    @Operation(summary = "Atualizar disponibilidade do produto")
    public ResponseEntity<ProdutoResponseDTO> atualizarDisponibilidade(
            @Parameter(description = "ID do produto") @PathVariable Long id,
            @Parameter(description = "Disponível (true/false)") @RequestParam Boolean disponivel) {
        return ResponseEntity.ok(produtoService.atualizarDisponibilidade(id, disponivel));
    }
}
