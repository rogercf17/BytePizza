package br.com.roger.api.bytepizza.loja;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.com.roger.api.bytepizza.loja.DTOs.CalculadoraAbertura;
import br.com.roger.api.bytepizza.loja.models.HorarioFuncionamento;
import org.junit.jupiter.api.Test;

class CalculadoraAberturaTest {

    // 14/10/2026 é uma quarta-feira.
    private static final LocalDateTime QUARTA = LocalDateTime.of(2026, 10, 14, 0, 0);

    private static List<HorarioFuncionamento> todosOsDias(LocalTime abre, LocalTime fecha) {
        List<HorarioFuncionamento> horarios = new ArrayList<>();
        for (short dia = 0; dia <= 6; dia++) {
            horarios.add(new HorarioFuncionamento(dia, abre, fecha));
        }
        return horarios;
    }

    @Test
    void abertaDentroDoHorario() {
        var horarios = todosOsDias(LocalTime.of(18, 0), LocalTime.of(23, 30));

        var resultado = CalculadoraAbertura.calcular(horarios, QUARTA.withHour(19));

        assertThat(resultado.aberta()).isTrue();
    }

    @Test
    void fechadaAntesDeAbrirInformaAberturaDeHoje() {
        var horarios = todosOsDias(LocalTime.of(18, 0), LocalTime.of(23, 30));

        var resultado = CalculadoraAbertura.calcular(horarios, QUARTA.withHour(17));

        assertThat(resultado.aberta()).isFalse();
        assertThat(resultado.proximaAbertura()).isEqualTo(QUARTA.withHour(18));
    }

    @Test
    void fechadaDepoisDeFecharInformaAberturaDeAmanha() {
        var horarios = todosOsDias(LocalTime.of(18, 0), LocalTime.of(23, 30));

        var resultado = CalculadoraAbertura.calcular(horarios, QUARTA.withHour(23).withMinute(45));

        assertThat(resultado.aberta()).isFalse();
        assertThat(resultado.proximaAbertura()).isEqualTo(QUARTA.plusDays(1).withHour(18));
    }

    @Test
    void horarioQuePassaDaMeiaNoiteContinuaAbertoNaMadrugada() {
        var horarios = todosOsDias(LocalTime.of(18, 0), LocalTime.of(2, 0));

        var resultado = CalculadoraAbertura.calcular(horarios, QUARTA.withHour(1));

        assertThat(resultado.aberta()).isTrue();
    }

    @Test
    void horarioQuePassaDaMeiaNoiteEstaFechadoDepoisDoFechamento() {
        var horarios = todosOsDias(LocalTime.of(18, 0), LocalTime.of(2, 0));

        var resultado = CalculadoraAbertura.calcular(horarios, QUARTA.withHour(3));

        assertThat(resultado.aberta()).isFalse();
    }

    @Test
    void semHorariosCadastradosFicaFechada() {
        var resultado = CalculadoraAbertura.calcular(List.of(), QUARTA.withHour(19));

        assertThat(resultado.aberta()).isFalse();
        assertThat(resultado.proximaAbertura()).isNull();
    }

    @Test
    void respeitaApenasOsDiasCadastrados() {
        // Só abre aos sábados (6).
        var horarios = List.of(new HorarioFuncionamento((short) 6, LocalTime.of(18, 0), LocalTime.of(23, 0)));

        var resultado = CalculadoraAbertura.calcular(horarios, QUARTA.withHour(19));

        assertThat(resultado.aberta()).isFalse();
        // Próximo sábado depois de 14/10/2026 (quarta) é 17/10/2026.
        assertThat(resultado.proximaAbertura()).isEqualTo(LocalDateTime.of(2026, 10, 17, 18, 0));
    }
}