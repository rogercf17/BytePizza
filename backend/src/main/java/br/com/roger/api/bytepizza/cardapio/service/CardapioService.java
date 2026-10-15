package br.com.roger.api.bytepizza.cardapio.service;

import br.com.roger.api.bytepizza.cardapio.DTOs.response.*;
import br.com.roger.api.bytepizza.cardapio.models.Categoria;
import br.com.roger.api.bytepizza.cardapio.models.Sabor;
import br.com.roger.api.bytepizza.cardapio.repository.SaborRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CardapioService {
    private final SaborRepository repository;

    @Transactional(readOnly = true)
    public CardapioResponse mostrarCardapio() {
        List<Sabor> sabores = repository.findAtivosComCategoria();

        Map<Long, List<Sabor>> porCategoria = new LinkedHashMap<>();

        for (Sabor s : sabores) {
            porCategoria
                    .computeIfAbsent(s.getCategoria().getId(), id -> new ArrayList<>())
                    .add(s);
        }

        List<CategoriaResponse> categorias = porCategoria.values().stream()
                .map(lista -> {
                    Categoria categoria = lista.get(0).getCategoria();
                    List<SaborResponse> itens = lista.stream().map(SaborResponse::de).toList();
                    return new CategoriaResponse(categoria.getId(), categoria.getNome(), itens);
                })
                .toList();

        CardapioResponse cardapio = new CardapioResponse(categorias);

        return cardapio;
    }
}
