package com.minhaempresa.pedidos.entity.enums;

import lombok.Getter;

@Getter
public enum TipoPagamentoEnum {
    CARTAO_CREDITO(1, "CARTAO_CREDITO"),
CARTAO_DEBITO(2, "CARTAO_DEBITO"),
    PIX_CREDITO(3, "PIX_CREDITO"),
    PIX_DEBITO(4, "PIX_DEBITO"),;

    private final int codigo;
    private final String descricao;

    TipoPagamentoEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static String valueOfEnumFromString(TipoPagamentoEnum tipoPagamento) {
        for (TipoPagamentoEnum tipo : TipoPagamentoEnum.values()) {
            if (tipo.codigo == tipoPagamento.codigo) {
                return tipo.getDescricao();
            }
        }
        return null;
    }

    public static TipoPagamentoEnum valueOfStringFromEnum(String tipoPagamento) {
        for (TipoPagamentoEnum tipo : TipoPagamentoEnum.values()) {
            if (tipo.descricao.equals(tipoPagamento)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo pagamento inválido: " + tipoPagamento);
    }

    public static String valueOfStringFromString(String tipoPagamento) {
        for (TipoPagamentoEnum tipo : TipoPagamentoEnum.values()) {
            if (tipo.descricao.equals(tipoPagamento)) {
                return tipo.getDescricao();
            }
        }
        throw new IllegalArgumentException("Tipo pagamento inválido: " + tipoPagamento);
    }

}
