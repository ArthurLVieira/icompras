package com.minhaempresa.pedidos.dto.response;

import com.minhaempresa.pedidos.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDtoResponse {
    private Long id;
    private Pedido pedido;
    private Long produtoId;
    private BigDecimal quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal total;
}
