package br.com.roger.api.bytepizza.cardapio.controller;

import br.com.roger.api.bytepizza.cardapio.DTOs.response.CardapioResponse;
import br.com.roger.api.bytepizza.cardapio.service.CardapioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bytepizza/cardapio")
@RequiredArgsConstructor
@Tag(name = "Cardápio")
public class CardapioController {
    private final CardapioService service;

    @GetMapping
    @Operation(summary = "Lista as pizzas ativas agrupadas por categoria")
    public ResponseEntity<CardapioResponse> mostrarCardapio() {
        return ResponseEntity.ok(service.mostrarCardapio());
    }


}
