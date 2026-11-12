package com.minhaempresa.pedidos.controller;

import com.minhaempresa.pedidos.dto.request.PagamentoDtoRequest;
import com.minhaempresa.pedidos.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("pedido/pagamento")
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoService service;

    @PutMapping
    public ResponseEntity<Void> updataPagamento(
            @RequestBody PagamentoDtoRequest request,
            @RequestHeader(required = true, name = "apiKey") String apiKey
    ) {
        service.updataPagamento(request);
        return ResponseEntity.ok().build();
    }

}
