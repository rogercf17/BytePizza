package br.com.roger.api.bytepizza.loja.service;

import br.com.roger.api.bytepizza.exceptions.RecursoNaoEncontrado;
import br.com.roger.api.bytepizza.loja.DTOs.CalculadoraAbertura;
import br.com.roger.api.bytepizza.loja.DTOs.StatusLojaResponse;
import br.com.roger.api.bytepizza.loja.models.ConfiguracaoLoja;
import br.com.roger.api.bytepizza.loja.models.HorarioFuncionamento;
import br.com.roger.api.bytepizza.loja.repository.ConfiguracaoLojaRepository;
import br.com.roger.api.bytepizza.loja.repository.HorarioFuncionamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LojaService {
    private final HorarioFuncionamentoRepository hfRepository;
    private final Clock clock;
    private final ConfiguracaoLojaRepository clRepository;
    private static final long ID_CONFIGURACAO = 1L;

    @Transactional(readOnly = true)
    public StatusLojaResponse mostraStatusLoja() {
        ConfiguracaoLoja configuracao = clRepository.findById(ID_CONFIGURACAO)
                .orElseThrow(() -> new RecursoNaoEncontrado("Configuração da loja não encontrada."));

        List<HorarioFuncionamento> horarios = hfRepository.findAll();

        LocalDateTime agora = LocalDateTime.now(clock);
        CalculadoraAbertura.Resultado resultado = CalculadoraAbertura.calcular(horarios, agora);

        boolean pausada = configuracao.isLojaPausada();
        boolean aberta = resultado.aberta() && !pausada;

        OffsetDateTime proximaAbertura = null;
        if (!aberta && !pausada && resultado.proximaAbertura() != null) {
            proximaAbertura = resultado.proximaAbertura().atZone(clock.getZone()).toOffsetDateTime();
        }

        StatusLojaResponse statusLoja = new StatusLojaResponse(
                aberta,
                pausada,
                proximaAbertura,
                configuracao.getPedidoMinimo(),
                configuracao.getPrazoMinMinutos(),
                configuracao.getPrazoMaxMinutos(),
                configuracao.getMensagemAviso()
        );
        return statusLoja;
    }
}
