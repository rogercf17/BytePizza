package br.com.roger.api.bytepizza.cardapio.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categoria")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false)
    private int ordem;

    @Column(nullable = false)
    private boolean ativa;
}
