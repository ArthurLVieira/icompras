package com.minhaempresa.produtos.controller;

import com.minhaempresa.produtos.controller.generic.Generics;
import com.minhaempresa.produtos.dto.ApiResponse;
import com.minhaempresa.produtos.dto.request.ProdutoDtoRequest;
import com.minhaempresa.produtos.dto.response.ProdutoDtoResponse;
import com.minhaempresa.produtos.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController implements Generics {
    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ApiResponse<ProdutoDtoResponse>> insert(
            @RequestBody @Valid ProdutoDtoRequest dto
    ) {
        return ResponseEntity.ok(
                ApiResponse.<ProdutoDtoResponse>builder()
                        .success(true)
                        .data(service.insert(dto))
                        .message("Produto inserido com sucesso")
                        .traceId(UUID.randomUUID().toString())
                        .path("/produtos")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<ProdutoDtoResponse>> findById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(
                ApiResponse.<ProdutoDtoResponse>builder()
                        .success(true)
                        .data(service.findById(id))
                        .message("Produto localizado com sucesso")
                        .traceId(UUID.randomUUID().toString())
                        .path("/produtos/" + id)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PatchMapping("{id}")
    public ResponseEntity<ApiResponse<ProdutoDtoResponse>> updateSaidaEstoque(
            @PathVariable("id") Long id,
            @RequestParam BigDecimal qtdSaida
    ) {
        return ResponseEntity.ok(
                ApiResponse.<ProdutoDtoResponse>builder()
                        .success(true)
                        .data(service.updateSaidaEstoque(qtdSaida, id))
                        .message("Produto atualizado com sucesso")
                        .traceId(UUID.randomUUID().toString())
                        .path("/produtos/" + id)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PatchMapping("estoque/{id}")
    public ResponseEntity<Void> updateEstoque(
            @PathVariable("id") Long id,
            @RequestParam("estoque") BigDecimal estoque
    ) {
        service.atualizaEstoque(estoque, id);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }
}
