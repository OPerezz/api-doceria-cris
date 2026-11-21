package com.doceria.service;

import com.doceria.dto.ProdutoDTO;
import com.doceria.dto.ProdutoResponseDTO;
import com.doceria.model.Categoria;
import com.doceria.model.Produto;
import com.doceria.repository.CategoriaRepository;
import com.doceria.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    
    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll(Sort.by("nome").ascending())
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    public Page<ProdutoResponseDTO> listarPaginado(Pageable pageable) {
        return produtoRepository.findByDisponivelTrue(pageable)
                .map(this::toResponseDTO);
    }
    
    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
        return toResponseDTO(produto);
    }
    
    public List<ProdutoResponseDTO> buscarPorCategoria(Long categoriaId) {
        return produtoRepository.findByCategoriaId(categoriaId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    public List<ProdutoResponseDTO> buscarDestaques() {
        return produtoRepository.findByDestaqueTrue()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    public Page<ProdutoResponseDTO> buscarAvancada(
            String termo,
            Long categoriaId,
            BigDecimal minPreco,
            BigDecimal maxPreco,
            Boolean apenasDestaque,
            Pageable pageable) {
        
        return produtoRepository.buscarAvancada(
                termo,
                categoriaId,
                minPreco,
                maxPreco,
                apenasDestaque,
                pageable
        ).map(this::toResponseDTO);
    }
    
    @Transactional
    public ProdutoResponseDTO criar(ProdutoDTO produtoDTO) {
        Categoria categoria = categoriaRepository.findById(produtoDTO.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + 
                        produtoDTO.getCategoriaId()));
        
        Produto produto = toEntity(produtoDTO, categoria);
        Produto saved = produtoRepository.save(produto);
        log.info("Produto criado: {}", saved.getNome());
        return toResponseDTO(saved);
    }
    
    @Transactional
    public ProdutoResponseDTO atualizar(Long id, ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
        
        Categoria categoria = categoriaRepository.findById(produtoDTO.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + 
                        produtoDTO.getCategoriaId()));
        
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setPrecoPromocional(produtoDTO.getPrecoPromocional());
        produto.setTempoPreparo(produtoDTO.getTempoPreparo());
        produto.setCategoria(categoria);
        produto.setIngredientes(produtoDTO.getIngredientes());
        produto.setDestaque(produtoDTO.getDestaque());
        produto.setDisponivel(produtoDTO.getDisponivel());
        produto.setPeso(produtoDTO.getPeso());
        produto.setImagem(produtoDTO.getImagem());
        
        Produto updated = produtoRepository.save(produto);
        log.info("Produto atualizado: {}", updated.getNome());
        return toResponseDTO(updated);
    }
    
    @Transactional
    public void deletar(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
        
        produtoRepository.delete(produto);
        log.info("Produto deletado: {}", produto.getNome());
    }
    
    @Transactional
    public ProdutoResponseDTO atualizarDisponibilidade(Long id, Boolean disponivel) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
        
        produto.setDisponivel(disponivel);
        Produto updated = produtoRepository.save(produto);
        log.info("Disponibilidade do produto {} atualizada para: {}", updated.getNome(), disponivel);
        return toResponseDTO(updated);
    }
    
    private ProdutoResponseDTO toResponseDTO(Produto produto) {
        return ProdutoResponseDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .precoPromocional(produto.getPrecoPromocional())
                .percentualDesconto(produto.getPercentualDesconto())
                .tempoPreparo(produto.getTempoPreparo())
                //.categoria(CategoriaService.toDTO(produto.getCategoria()))
                .ingredientes(produto.getIngredientes())
                .destaque(produto.getDestaque())
                .disponivel(produto.getDisponivel())
                .peso(produto.getPeso())
                .imagem(produto.getImagem())
                .avaliacao(produto.getAvaliacao())
                .totalVendidos(produto.getTotalVendidos())
                .build();
    }
    
    private Produto toEntity(ProdutoDTO dto, Categoria categoria) {
        return Produto.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .precoPromocional(dto.getPrecoPromocional())
                .tempoPreparo(dto.getTempoPreparo())
                .categoria(categoria)
                .ingredientes(dto.getIngredientes())
                .destaque(dto.getDestaque() != null ? dto.getDestaque() : false)
                .disponivel(dto.getDisponivel() != null ? dto.getDisponivel() : true)
                .peso(dto.getPeso())
                .imagem(dto.getImagem())
                .build();
    }
}