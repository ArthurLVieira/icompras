package com.minhaempresa.pedidos.controller;

import com.minhaempresa.pedidos.dto.ApiResponse;
import com.minhaempresa.pedidos.dto.request.ItemPedidoDtoRequest;
import com.minhaempresa.pedidos.dto.response.ItemPedidoDtoResponse;
import com.minhaempresa.pedidos.service.ItemPedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("itensPedido")
@RequiredArgsConstructor
public class ItemPedidoController {
    private final ItemPedidoService service;

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<ItemPedidoDtoResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.<ItemPedidoDtoResponse>builder()
                        .success(true)
                        .data(service.findById(id))
                        .message("Item pedido encontrado")
                        .traceId(UUID.randomUUID().toString())
                        .path("/itens-pedido/{id}")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("pedido/{id}")
    public ResponseEntity<ApiResponse<Page<ItemPedidoDtoResponse>>> findByPedidoId(
            @PathVariable Long id,
            @PageableDefault(size = 20, sort = "id") Pageable pageable
    ) {
        Page<ItemPedidoDtoResponse> page = service.findByPedidoId(id, pageable);
        return ResponseEntity.ok(
                ApiResponse.<Page<ItemPedidoDtoResponse>>builder()
                        .success(true)
                        .data(page)
                        .message("Item pedido encontrado")
                        .traceId(UUID.randomUUID().toString())
                        .path("/itens-pedido/pedido/{id}")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ItemPedidoDtoResponse>> insert(
            @RequestBody @Valid ItemPedidoDtoRequest dto
    ) {
        return ResponseEntity.ok(
                ApiResponse.<ItemPedidoDtoResponse>builder()
                        .success(true)
                        .data(service.insert(dto))
                        .message("Item pedido inserido com sucesso")
                        .traceId(UUID.randomUUID().toString())
                        .path("/itens-pedido/{id}")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PutMapping("{id}")
    public  ResponseEntity<ApiResponse<ItemPedidoDtoResponse>> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid ItemPedidoDtoRequest dto
    ) {
        return ResponseEntity.ok(
                ApiResponse.<ItemPedidoDtoResponse>builder()
                        .success(true)
                        .data(service.update(dto, id))
                        .message("Item pedido atualizado com sucesso")
                        .traceId(UUID.randomUUID().toString())
                        .path("/itens-pedido/{id}")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
