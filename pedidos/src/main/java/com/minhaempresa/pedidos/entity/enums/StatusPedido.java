package com.minhaempresa.pedidos.entity.enums;

import lombok.Getter;

@Getter
public enum StatusPedido {
    REALIZADO(1, "REALIZADO"),
    FATURADO(2, "FATURADO"),
    ENVIADO(3, "ENVIADO"),
    ERRO_PAGAMENTO(4, "ERRO_PAGAMENTO"),
    PAGAMENTO_ENVIADO(5, "PAGAMENTO_ENVIADO");

    private final int codigo;
    private final String descricao;

    StatusPedido(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static StatusPedido valueOfCodigo(int codigo) {
        for (StatusPedido item: StatusPedido.values()) {
            if (item.codigo == codigo) {
                return item;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }

    public static String valueOfStatus(StatusPedido status) {
        for (StatusPedido item: StatusPedido.values()) {
            if (item.codigo == status.ordinal()) {
                return item.descricao;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + status.toString());
    }

    public static StatusPedido valueOfDescricao(String descricao) {
        for (StatusPedido item: StatusPedido.values()) {
            if (item.descricao.equals(descricao)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Descrição inválida: " + descricao);
    }

    public static String valueOfStringForString(String descricao) {
        for (StatusPedido item: StatusPedido.values()) {
            if (item.descricao.equals(descricao)) {
                return item.descricao;
            }
        }
        throw new IllegalArgumentException("Descrição inválida: " + descricao);
    }
}
