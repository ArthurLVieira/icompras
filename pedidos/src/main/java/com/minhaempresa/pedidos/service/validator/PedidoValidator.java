package com.minhaempresa.pedidos.service.validator;

import com.minhaempresa.pedidos.client.ClienteClient;
import com.minhaempresa.pedidos.entity.ItemPedido;
import com.minhaempresa.pedidos.entity.Pedido;
import com.minhaempresa.pedidos.entity.enums.StatusPedido;
import com.minhaempresa.pedidos.exception.BusinessException;
import com.minhaempresa.pedidos.exception.enuns.ErrorCode;
import com.minhaempresa.pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoValidator {
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoValidator itemPedidoValidator;
    private final ClienteClient clienteClient;

    public Pedido validateEntityId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() ->
                new BusinessException(ErrorCode.PEDIDO_NOT_FOUND));
    }

    public void validateEntity(Pedido entity, Long id) {
        validateItemPedido(entity.getItens());
        validateClientePedido(entity.getClienteId());
    }

    private void validateItemPedido(List<ItemPedido> itens) {
        for (ItemPedido item : itens) {
            itemPedidoValidator.validateEntity(item);
        }
    }

    public void baixaEstoquePedido(Pedido pedido) {
        for (ItemPedido item: pedido.getItens()) {
            itemPedidoValidator.baixaEstoqueItemPedido(item);
        }
    }

    private void validateClientePedido(Long clienteId) {
        clienteClient.findById(clienteId);
    }

    public void updateData(Pedido newEntity, Pedido currentEntity) {
        currentEntity.setChavePagamento(newEntity.getChavePagamento());
        currentEntity.setClienteId(newEntity.getClienteId());
        currentEntity.setObservacao(newEntity.getObservacao());
        currentEntity.setStatus(StatusPedido.valueOfDescricao(newEntity.getStatus()));
    }

    public void calcularTotalPedido(Pedido entity) {
        for (ItemPedido item : entity.getItens()) {
            entity.setTotal(entity.getTotal().add(item.getTotal()));
        }
    }

    public void calcularTotalItensPedido(Pedido entity) {
        entity.getItens().forEach(itemPedidoValidator::calcularTotalItem);
    }
}
