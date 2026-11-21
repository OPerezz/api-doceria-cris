package com.doceria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioDTO {
    
    private Long totalProdutos;
    private Long categoriasAtivas;
    private BigDecimal precoTotal;
    private BigDecimal precoMedio;
    private ProdutoResumidoDTO produtoMaisCaro;
    private ProdutoResumidoDTO produtoMaisBarato;
    private ProdutoMaisVendidoDTO produtoMaisVendido;
    private Long produtosComDesconto;
    private Double avaliacaoMedia;
    private Map<String, Object> estatisticasPorCategoria;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProdutoResumidoDTO {
        private String nome;
        private BigDecimal preco;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProdutoMaisVendidoDTO {
        private String nome;
        private Integer vendidos;
        private String categoria;
    }
}
