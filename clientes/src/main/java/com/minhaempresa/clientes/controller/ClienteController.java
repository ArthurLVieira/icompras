package com.minhaempresa.clientes.controller;

import com.minhaempresa.clientes.dto.ApiResponse;
import com.minhaempresa.clientes.dto.request.ClienteDtoRequest;
import com.minhaempresa.clientes.dto.response.ClienteDtoResponse;
import com.minhaempresa.clientes.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<ClienteDtoResponse>> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                ApiResponse.<ClienteDtoResponse>builder()
                        .success(true)
                        .data(service.findById(id))
                        .message("Cliente localizado com sucesso.")
                        .traceId(UUID.randomUUID().toString())
                        .path("/clientes")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClienteDtoResponse>> insert(
            @Valid @RequestBody ClienteDtoRequest request
    ) {
        return ResponseEntity.ok(
                ApiResponse.<ClienteDtoResponse>builder()
                        .success(true)
                        .data(service.insert(request))
                        .message("Cliente inserido com sucesso.")
                        .traceId(UUID.randomUUID().toString())
                        .path("/clientes")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("cpf/{cpf}")
    public ResponseEntity<ApiResponse<ClienteDtoResponse>> findByCpf(
            @PathVariable(value = "cpf") String cpf
    ) {
        return ResponseEntity.ok(
                ApiResponse.<ClienteDtoResponse>builder()
                        .success(true)
                        .data(service.findByCpf(cpf))
                        .message("Cliente localizado com sucesso.")
                        .traceId(UUID.randomUUID().toString())
                        .path("/clientes")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}