package com.minhaempresa.pedidos.exception;


import com.minhaempresa.pedidos.exception.enuns.ErrorCode;

public class BusinessException extends RuntimeException{
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode){
        super(errorCode.name());
        this.errorCode = errorCode;
    }

    public ErrorCode getError() {
        return errorCode;
    }

    public String getErrorMessage() {return errorCode.getMessage();}

    public String getErrrorCode() {return errorCode.getCode();}

}
