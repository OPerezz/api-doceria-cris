package com.doceria.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDTO {
    
    private Long id;
    
    @NotNull(message = "Produto é obrigatório")
    private Long produtoId;
    
    @NotBlank(message = "Nome do usuário é obrigatório")
    @Size(max = 100, message = "Nome do usuário deve ter no máximo 100 caracteres")
    private String usuario;
    
    @NotNull(message = "Nota é obrigatória")
    @Min(value = 1, message = "Nota mínima é 1")
    @Max(value = 5, message = "Nota máxima é 5")
    private Integer nota;
    
    @Size(max = 500, message = "Comentário deve ter no máximo 500 caracteres")
    private String comentario;
    
    private LocalDate data;
}