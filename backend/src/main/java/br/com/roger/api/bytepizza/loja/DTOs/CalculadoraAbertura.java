package br.com.roger.api.bytepizza.loja.DTOs;

import br.com.roger.api.bytepizza.loja.models.HorarioFuncionamento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public final class CalculadoraAbertura {
    private CalculadoraAbertura() {}

    public record Resultado(boolean aberta, LocalDateTime proximaAbertura) {}

    public static Resultado calcular(List<HorarioFuncionamento> horarios, LocalDateTime agora) {
        boolean aberta = false;
        LocalDateTime proxima = null;

        for (int deslocamento = -1; deslocamento <= 7; deslocamento++) {
            LocalDate data = agora.toLocalDate().plusDays(deslocamento);
            int dia = data.getDayOfWeek().getValue() % 7;

            for (HorarioFuncionamento h : horarios) {
                if (h.getDiaSemana() != dia) {
                    continue;
                }
                LocalDateTime abre = data.atTime(h.getAbre());
                LocalDateTime fecha = h.getFecha().isAfter(h.getAbre())
                        ? data.atTime(h.getFecha())
                        : data.plusDays(1).atTime(h.getFecha());

                if(!agora.isBefore(abre) && agora.isBefore(fecha)) {
                    aberta = true;
                }

                if (abre.isAfter(agora) && (proxima == null || abre.isBefore(proxima))) {
                    proxima = abre;
                }
            }
        }
        return new Resultado(aberta, proxima);
    }
}
