package com.minhaempresa.pedidos.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDtoRequest {
    private Long pedidoId;
    private Long produtoId;
    private BigDecimal quantidade;
    private BigDecimal valorUnitario;
}
