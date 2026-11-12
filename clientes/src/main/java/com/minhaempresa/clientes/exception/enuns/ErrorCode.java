package com.minhaempresa.clientes.exception.enuns;

import com.minhaempresa.clientes.dto.ApiError;
import lombok.Getter;

@Getter
public enum ErrorCode {

    CLIENTE_NOT_FOUND("CLIENTE-001", "Cliente não encontrado."),
    CLIENTE_ALREADY_EXISTS("CLIENTE-OO2", "Cliente existente."),
    CLIENTE_ALREADY_EXISTS_BY_CPF("CLIENTE-OO3", "Cliente existente com o mesmo CPF."),
    CLIENTE_NOT_FOUND_BY_CPF("CLIENTE-004", "Cliente não encontrado para CPF."),;

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
