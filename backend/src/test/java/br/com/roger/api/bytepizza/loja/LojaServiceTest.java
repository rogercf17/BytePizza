package br.com.roger.api.bytepizza.loja;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.roger.api.bytepizza.loja.models.ConfiguracaoLoja;
import br.com.roger.api.bytepizza.loja.repository.ConfiguracaoLojaRepository;
import br.com.roger.api.bytepizza.loja.repository.HorarioFuncionamentoRepository;
import br.com.roger.api.bytepizza.loja.service.LojaService;
import org.junit.jupiter.api.Test;
import br.com.roger.api.bytepizza.loja.models.HorarioFuncionamento;

import br.com.roger.api.bytepizza.exceptions.RecursoNaoEncontrado;

class LojaServiceTest {

    private static final ZoneId FUSO = ZoneId.of("America/Sao_Paulo");

    private final ConfiguracaoLojaRepository configuracaoRepository = mock(ConfiguracaoLojaRepository.class);
    private final HorarioFuncionamentoRepository horarioRepository = mock(HorarioFuncionamentoRepository.class);

    private LojaService servicoNoHorario(int hora, boolean pausada) {
        ConfiguracaoLoja configuracao = mock(ConfiguracaoLoja.class);
        when(configuracao.isLojaPausada()).thenReturn(pausada);
        when(configuracao.getPedidoMinimo()).thenReturn(new BigDecimal("30.00"));
        when(configuracao.getPrazoMinMinutos()).thenReturn(30);
        when(configuracao.getPrazoMaxMinutos()).thenReturn(45);
        when(configuracaoRepository.findById(1L)).thenReturn(Optional.of(configuracao));

        List<HorarioFuncionamento> horarios = new ArrayList<>();
        for (short dia = 0; dia <= 6; dia++) {
            horarios.add(new HorarioFuncionamento(dia, LocalTime.of(18, 0), LocalTime.of(23, 30)));
        }
        when(horarioRepository.findAll()).thenReturn(horarios);

        Clock relogio = Clock.fixed(ZonedDateTime.of(2026, 10, 14, hora, 0, 0, 0, FUSO).toInstant(), FUSO);
        return new LojaService(horarioRepository, relogio, configuracaoRepository);
    }

    @Test
    void abertaNoHorario() {
        var status = servicoNoHorario(19, false).mostraStatusLoja();

        assertThat(status.aberta()).isTrue();
        assertThat(status.pausada()).isFalse();
        assertThat(status.proximaAbertura()).isNull();
        assertThat(status.pedidoMinimo()).isEqualByComparingTo("30.00");
    }

    @Test
    void fechadaForaDoHorarioInformaProximaAbertura() {
        var status = servicoNoHorario(10, false).mostraStatusLoja();

        assertThat(status.aberta()).isFalse();
        assertThat(status.proximaAbertura()).isNotNull();
        assertThat(status.proximaAbertura().getHour()).isEqualTo(18);
    }

    @Test
    void pausadaManualmenteFechaMesmoDentroDoHorario() {
        var status = servicoNoHorario(19, true).mostraStatusLoja();

        assertThat(status.aberta()).isFalse();
        assertThat(status.pausada()).isTrue();
        assertThat(status.proximaAbertura()).isNull();
    }

    @Test
    void semConfiguracaoLancaErro() {
        when(configuracaoRepository.findById(1L)).thenReturn(Optional.empty());
        var servico = new LojaService(horarioRepository, Clock.system(FUSO), configuracaoRepository);

        assertThatThrownBy(servico::mostraStatusLoja).isInstanceOf(RecursoNaoEncontrado.class);
    }
}