package br.com.roger.api.bytepizza.cardapio.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "sabor")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Sabor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(nullable = false, length = 80)
    private String nome;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(nullable = false)
    private boolean vegetariano;

    @Column(nullable = false, length = 255)
    private String imagemUrl;

    @Column(nullable = false)
    private boolean ativo;
}
