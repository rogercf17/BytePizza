package br.com.roger.api.bytepizza.cardapio.DTOs.response;

import br.com.roger.api.bytepizza.cardapio.models.Sabor;

import java.math.BigDecimal;

public record SaborResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        boolean vegetariano,
        String imagemUrl
) {
    public static SaborResponse de(Sabor sabor) {
        return new SaborResponse(
                sabor.getId(),
                sabor.getNome(),
                sabor.getDescricao(),
                sabor.getPreco(),
                sabor.isVegetariano(),
                sabor.getImagemUrl()
        );
    }
}
