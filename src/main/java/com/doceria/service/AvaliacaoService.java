package com.doceria.service;

import com.doceria.dto.AvaliacaoDTO;
import com.doceria.model.Avaliacao;
import com.doceria.model.Produto;
import com.doceria.repository.AvaliacaoRepository;
import com.doceria.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AvaliacaoService {
    
    private final AvaliacaoRepository avaliacaoRepository;
    private final ProdutoRepository produtoRepository;
    
    public List<AvaliacaoDTO> listarPorProduto(Long produtoId) {
        return avaliacaoRepository.findByProdutoId(produtoId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<AvaliacaoDTO> listarUltimasPorProduto(Long produtoId, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return avaliacaoRepository.findUltimasAvaliacoesPorProduto(produtoId, pageable)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public AvaliacaoDTO criar(AvaliacaoDTO avaliacaoDTO) {
        Produto produto = produtoRepository.findById(avaliacaoDTO.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + 
                        avaliacaoDTO.getProdutoId()));
        
        Avaliacao avaliacao = toEntity(avaliacaoDTO, produto);
        Avaliacao saved = avaliacaoRepository.save(avaliacao);
        
        // Atualizar média do produto
        atualizarMediaProduto(produto);
        
        log.info("Avaliação criada para o produto: {}", produto.getNome());
        return toDTO(saved);
    }
    
    @Transactional
    public void deletar(Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada com ID: " + id));
        
        Long produtoId = avaliacao.getProduto().getId();
        avaliacaoRepository.delete(avaliacao);
        
        // Recalcular média
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        atualizarMediaProduto(produto);
        
        log.info("Avaliação deletada");
    }
    
    private void atualizarMediaProduto(Produto produto) {
        Double media = avaliacaoRepository.calcularMediaNotaPorProduto(produto.getId());
        
        if (media != null) {
            BigDecimal mediaFormatada = BigDecimal.valueOf(media)
                    .setScale(2, RoundingMode.HALF_UP);
            produto.setAvaliacao(mediaFormatada);
        } else {
            produto.setAvaliacao(BigDecimal.ZERO);
        }
        
        produtoRepository.save(produto);
    }
    
    private AvaliacaoDTO toDTO(Avaliacao avaliacao) {
        return AvaliacaoDTO.builder()
                .id(avaliacao.getId())
                .produtoId(avaliacao.getProduto().getId())
                .usuario(avaliacao.getUsuario())
                .nota(avaliacao.getNota())
                .comentario(avaliacao.getComentario())
                .data(avaliacao.getData())
                .build();
    }
    
    private Avaliacao toEntity(AvaliacaoDTO dto, Produto produto) {
        return Avaliacao.builder()
                .produto(produto)
                .usuario(dto.getUsuario())
                .nota(dto.getNota())
                .comentario(dto.getComentario())
                .data(dto.getData() != null ? dto.getData() : LocalDate.now())
                .build();
    }
}