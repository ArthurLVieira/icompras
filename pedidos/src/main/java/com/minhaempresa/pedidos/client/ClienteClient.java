package com.minhaempresa.pedidos.client;

import com.minhaempresa.pedidos.client.representation.ClienteRepresentation;
import com.minhaempresa.pedidos.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "clientes", url = "${spring.client.config.cliente.url}")
public interface ClienteClient {

    @GetMapping("{id}")
    ApiResponse<ClienteRepresentation> findById(@PathVariable("id") Long id);

    @GetMapping("cpf/{cpf}")
    ApiResponse<ClienteRepresentation> findByCpf(@PathVariable("cpf") String cpf);
}
