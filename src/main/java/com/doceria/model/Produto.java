package com.doceria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produtos")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 200)
    private String nome;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
    
    @Column(name = "preco_promocional", precision = 10, scale = 2)
    private BigDecimal precoPromocional;
    
    @Column(name = "tempo_preparo", nullable = false)
    private Integer tempoPreparo; // em minutos
    
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    
    @Column(columnDefinition = "TEXT")
    private String ingredientes;
    
    @Builder.Default
    private Boolean destaque = false;
    
    @Builder.Default
    private Boolean disponivel = true;
    
    @Column(length = 50)
    private String peso;
    
    @Column(length = 255)
    private String imagem;
    
    @Builder.Default
    @Column(precision = 3, scale = 2)
    private BigDecimal avaliacao = BigDecimal.ZERO;
    
    @Builder.Default
    @Column(name = "total_vendidos")
    private Integer totalVendidos = 0;
    
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Avaliacao> avaliacoes;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Método para calcular desconto percentual
    @Transient
    public Integer getPercentualDesconto() {
        if (precoPromocional != null && preco.compareTo(BigDecimal.ZERO) > 0) {
            return preco.subtract(precoPromocional)
                    .multiply(BigDecimal.valueOf(100))
                    .divide(preco, 0, java.math.RoundingMode.HALF_UP)
                    .intValue();
        }
        return 0;
    }
}
