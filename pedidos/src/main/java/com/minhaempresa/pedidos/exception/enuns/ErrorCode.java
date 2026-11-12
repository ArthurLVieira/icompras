package com.minhaempresa.pedidos.exception.enuns;

import com.minhaempresa.pedidos.dto.ApiError;
import lombok.Getter;

@Getter
public enum ErrorCode {

    PEDIDO_NOT_FOUND("PEDIDO-001", "Pedido não encontrado."),
    PEDIDO_ALREADY_EXISTS("PEDIDO-OO2", "Pedido existente."),
    ITEM_PEDIDO_QTD_ZERO("ITEM_PEDIDO-001", "Item pedido com quantidade zero"),
    ITEM_PEDIDO_PRECO_ZERO("ITEM_PEDIDO-002", "Item pedido com preço zero"),
    ITEM_PEDIDO_NOT_FOUND("ITEM_PEDIDO-003", "Item pedido não encontrado."),
    PRODUTO_UNAVAILABLE_ESTOQUE("PRODUTO-001", "Produto com saúdo indisponível."),
    PRODUTO_ZERO_BALANCE("PRODUTO-002", "Produto com saúdo zerado"),;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiError toError() {
        return new ApiError(code, message);
    }
}
