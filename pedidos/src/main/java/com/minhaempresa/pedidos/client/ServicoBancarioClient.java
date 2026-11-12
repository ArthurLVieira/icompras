package com.minhaempresa.pedidos.client;

import com.minhaempresa.pedidos.entity.Pedido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ServicoBancarioClient {

    public String solicitarPagamento(Pedido pedido) {
        return UUID.randomUUID().toString();
    }
}
