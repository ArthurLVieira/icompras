package com.minhaempresa.pedidos.dto.request;

import com.minhaempresa.pedidos.entity.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Text;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDtoRequest {
    private Long clienteId;
    private List<ItemPedido> itens;
    private LocalDateTime dataPedido;
    private String observacao;
}
