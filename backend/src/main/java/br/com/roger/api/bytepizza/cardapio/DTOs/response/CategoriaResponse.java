package br.com.roger.api.bytepizza.cardapio.DTOs.response;

import java.util.List;

public record CategoriaResponse(
        Long id,
        String nome,
        List<SaborResponse> sabores
) { }
