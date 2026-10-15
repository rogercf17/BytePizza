package br.com.roger.api.bytepizza.loja;

import br.com.roger.api.bytepizza.loja.DTOs.StatusLojaResponse;
import br.com.roger.api.bytepizza.loja.service.LojaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bytepizza/loja")
@Tag(name = "Loja")
@RequiredArgsConstructor
public class LojaController {
    private final LojaService service;

    @GetMapping("/status")
    @Operation(summary = "Informa se a loja está aberta, o pedido mínimo e o prazo estimado")
    public ResponseEntity<StatusLojaResponse> mostraStatusLoja() {
        return ResponseEntity.ok(service.mostraStatusLoja());
    }
}
