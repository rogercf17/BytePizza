package br.com.roger.api.bytepizza.cardapio.DTOs.response;

import java.util.List;

public record CardapioResponse(
        List<CategoriaResponse> categorias
) { }
