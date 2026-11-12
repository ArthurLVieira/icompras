package com.minhaempresa.pedidos.dto.response;

import com.minhaempresa.pedidos.entity.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDtoResponse {
    private Long id;
    private Long clienteId;
    private List<ItemPedido> itens;
    private BigDecimal total;
    private LocalDateTime dataPedido;
    private String chavePagamento;
    private String observacao;
    private String status;
}
