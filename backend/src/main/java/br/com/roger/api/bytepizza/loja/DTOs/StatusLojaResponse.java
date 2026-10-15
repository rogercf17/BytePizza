package br.com.roger.api.bytepizza.loja.DTOs;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record StatusLojaResponse(
        boolean aberta,
        boolean pausada,
        OffsetDateTime proximaAbertura,
        BigDecimal pedidoMinimo,
        int praxoMinMinutos,
        int prazoMaxMinutos,
        String mensagemAviso
) { }
