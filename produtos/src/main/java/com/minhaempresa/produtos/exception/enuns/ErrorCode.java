package com.minhaempresa.produtos.exception.enuns;

import com.minhaempresa.produtos.dto.ApiError;
import lombok.Getter;

@Getter
public enum ErrorCode {

    PRODUTO_NOT_FOUND("PRODUTO-001", "Produto não encontrado."),
    PRODUTO_ALREADY_EXISTS("PRODUTO-OO2", "Produto existente."),
    PRODUTO_ALREADY_EXISTS_BY_COD_BARRAS("PRODUTO-OO3", "Produto existente com o código de barras."),
    PRODUTO_UNAVAILABLE_ESTOQUE("PRODUTO-004", "Produto com saúdo indisponível."),
    PRODUTO_ZERO_BALANCE("PRODUTO-005", "Produto com saúdo zerado");

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
