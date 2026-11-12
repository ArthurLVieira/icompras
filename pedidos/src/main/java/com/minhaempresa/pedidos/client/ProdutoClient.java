package com.minhaempresa.pedidos.client;

import com.minhaempresa.pedidos.client.representation.ProdutoRepresentation;
import com.minhaempresa.pedidos.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "produtos", url = "${spring.client.config.produto.url}")
public interface ProdutoClient {

    @GetMapping("{id}")
    ApiResponse<ProdutoRepresentation> findById(@PathVariable("id") Long id);

    @PatchMapping("{id}")
    ResponseEntity<ApiResponse<ProdutoRepresentation>> updateSaidaEstoque(
            @PathVariable("id") Long id,
            @RequestParam BigDecimal qtdSaida
    );

    @PatchMapping("estoque/{id}")
    ResponseEntity<Void> updateEstoque(
            @PathVariable("id") Long id,
            @RequestParam BigDecimal estoque);
}
