package com.minhaempresa.pedidos.client.representation;

import java.math.BigDecimal;


public record ProdutoRepresentation (
        Long id,
        String descricao,
        String codBarras,
        BigDecimal valorUnitario,
        BigDecimal quantidadeEstoque,
        BigDecimal valorCusto
) {}
