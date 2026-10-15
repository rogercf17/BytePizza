package br.com.roger.api.bytepizza.loja.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "configuracao_loja")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ConfiguracaoLoja {
    @Id
    private Long id;

    @Column(name = "pedido_minimo", nullable = false, precision = 10, scale = 2)
    private BigDecimal pedidoMinimo;

    @Column(name = "loja_pausada", nullable = false)
    private boolean lojaPausada;

    @Column(name = "validade_pix_minutos", nullable = false)
    private int validadePixMinutos;

    @Column(name = "mensagem_aviso", length = 255)
    private String mensagemAviso;

    @Column(name = "latitude_loja", nullable = false, precision = 9, scale = 6)
    private BigDecimal latitudeLoja;

    @Column(name = "longitude_loja", nullable = false, precision = 9, scale = 6)
    private BigDecimal longitudeLoja;

    @Column(name = "taxa_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal taxaBase;

    @Column(name = "raio_taxa_base_km", nullable = false, precision = 4, scale = 1)
    private BigDecimal raioTaxaBaseKm;

    @Column(name = "taxa_por_km_excedente", nullable = false, precision = 10, scale = 2)
    private BigDecimal taxaPorKmExcedente;

    @Column(name = "prazo_min_minutos", nullable = false)
    private int prazoMinMinutos;

    @Column(name = "prazo_max_minutos", nullable = false)
    private int prazoMaxMinutos;
}
