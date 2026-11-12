package com.minhaempresa.pedidos.controller;

import com.minhaempresa.pedidos.client.representation.ClienteRepresentation;
import com.minhaempresa.pedidos.dto.ApiResponse;
import com.minhaempresa.pedidos.dto.request.PedidoDtoRequest;
import com.minhaempresa.pedidos.dto.response.PedidoDtoResponse;
import com.minhaempresa.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService service;

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<PedidoDtoResponse>> findById(@PathVariable("id")  Long id) {
        return ResponseEntity.ok(
                ApiResponse.<PedidoDtoResponse>builder()
                        .success(true)
                        .data(service.findById(id))
                        .message("Pedido encontrado com sucesso")
                        .traceId(UUID.randomUUID().toString())
                        .path("/pedidos/{id}")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PedidoDtoResponse>> insert(
            @RequestBody @Valid PedidoDtoRequest dto
    ) {
        return ResponseEntity.ok(
                ApiResponse.<PedidoDtoResponse>builder()
                        .success(true)
                        .data(service.insert(dto))
                        .message("Pedido inserido com sucesso")
                        .traceId(UUID.randomUUID().toString())
                        .path("/pedidos/{id}")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PutMapping("{id}")
    public  ResponseEntity<ApiResponse<PedidoDtoResponse>> update(
            @RequestBody @Valid PedidoDtoRequest dto,
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(
                ApiResponse.<PedidoDtoResponse>builder()
                        .success(true)
                        .data(service.update(dto, id))
                        .message("Pedido atualizado com sucesso")
                        .traceId(UUID.randomUUID().toString())
                        .path("/pedidos/{id}")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<ApiResponse<ClienteRepresentation>> findClienteById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(
                ApiResponse.<ClienteRepresentation>builder()
                        .success(true)
                        .data(service.findClienteById(id))
                        .message("Cliente encontrado com sucesso")
                        .traceId(UUID.randomUUID().toString())
                        .path("/cliente/{id}")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
