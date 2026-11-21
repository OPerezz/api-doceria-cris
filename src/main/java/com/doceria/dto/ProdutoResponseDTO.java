package com.doceria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseDTO {
    
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private BigDecimal precoPromocional;
    private Integer percentualDesconto;
    private Integer tempoPreparo;
    private CategoriaDTO categoria;
    private String ingredientes;
    private Boolean destaque;
    private Boolean disponivel;
    private String peso;
    private String imagem;
    private BigDecimal avaliacao;
    private Integer totalVendidos;
    private List<AvaliacaoDTO> avaliacoes;
}
