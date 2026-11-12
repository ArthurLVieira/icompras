package com.minhaempresa.pedidos.service.validator;

import com.minhaempresa.pedidos.client.ProdutoClient;
import com.minhaempresa.pedidos.client.representation.ProdutoRepresentation;
import com.minhaempresa.pedidos.dto.ApiResponse;
import com.minhaempresa.pedidos.entity.ItemPedido;
import com.minhaempresa.pedidos.exception.BusinessException;
import com.minhaempresa.pedidos.exception.enuns.ErrorCode;
import com.minhaempresa.pedidos.repository.ItemPedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ItemPedidoValidator {
    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoClient produtoClient;

    public ItemPedido validateEntityId(Long id) {
        return itemPedidoRepository.findById(id).orElseThrow(() ->
                new BusinessException(ErrorCode.ITEM_PEDIDO_NOT_FOUND));
    }

    public void validateEntity(ItemPedido entity) {
        if (entity.getQuantidade().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(ErrorCode.ITEM_PEDIDO_QTD_ZERO);
        }

        if (entity.getValorUnitario().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(ErrorCode.ITEM_PEDIDO_PRECO_ZERO);
        }

        ApiResponse<ProdutoRepresentation> response = produtoClient.findById(entity.getProdutoId());
        ProdutoRepresentation produto = response.getData();

        if (entity.getQuantidade().compareTo(produto.quantidadeEstoque()) > 0) {
            throw new BusinessException(ErrorCode.PRODUTO_UNAVAILABLE_ESTOQUE);
        }
    }

    public void baixaEstoqueItemPedido(ItemPedido entity) {
        produtoClient.updateSaidaEstoque(entity.getProdutoId(), entity.getQuantidade());
    }

    public void updateData(ItemPedido newEntity, ItemPedido currentEntity) {
        validateProdutoUpdate(newEntity, currentEntity);
        currentEntity.setValorUnitario(newEntity.getValorUnitario());
        currentEntity.setProdutoId(newEntity.getProdutoId());
    }

    private void validateProdutoUpdate(ItemPedido newEntity, ItemPedido currentEntity) {
        if (!Objects.equals(newEntity.getProdutoId(), currentEntity.getProdutoId())) {
            produtoClient.updateEstoque(currentEntity.getProdutoId(), currentEntity.getQuantidade());
            currentEntity.setQuantidade(newEntity.getQuantidade());
            baixaEstoqueItemPedido(newEntity);

            return;
        }

        produtoClient.updateEstoque(
                currentEntity.getProdutoId(),
                currentEntity.getQuantidade().subtract(newEntity.getQuantidade())
        );

        currentEntity.setQuantidade(newEntity.getQuantidade());
    }

    public void calcularTotalItem(ItemPedido item) {
        item.setTotal(item.getQuantidade().multiply(item.getValorUnitario()));
    }
}
