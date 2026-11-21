package com.doceria.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 200, message = "Nome deve ter no máximo 200 caracteres")
    private String nome;
    
    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    private String descricao;
    
    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    @DecimalMax(value = "999999.99", message = "Preço muito alto")
    private BigDecimal preco;
    
    @DecimalMin(value = "0.00", message = "Preço promocional deve ser maior ou igual a zero")
    private BigDecimal precoPromocional;
    
    @NotNull(message = "Tempo de preparo é obrigatório")
    @Min(value = 1, message = "Tempo de preparo deve ser maior que zero")
    private Integer tempoPreparo;
    
    @NotNull(message = "Categoria é obrigatória")
    private Long categoriaId;
    
    private String ingredientes;
    private Boolean destaque;
    private Boolean disponivel;
    private String peso;
    private String imagem;
}
