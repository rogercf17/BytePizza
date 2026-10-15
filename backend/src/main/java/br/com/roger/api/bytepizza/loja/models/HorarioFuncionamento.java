package br.com.roger.api.bytepizza.loja.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

@Entity
@Table(name = "horario_funcionamento")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class HorarioFuncionamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dia_semana", nullable = false)
    private short diaSemana;

    @Column(nullable = false)
    private LocalTime abre;

    @Column(nullable = false)
    private LocalTime fecha;

    public HorarioFuncionamento(short diaSemana, LocalTime abre, LocalTime fecha) {
        this.diaSemana = diaSemana;
        this.abre = abre;
        this.fecha = fecha;
    }
}
