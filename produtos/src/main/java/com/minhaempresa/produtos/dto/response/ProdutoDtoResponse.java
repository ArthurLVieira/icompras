package com.minhaempresa.produtos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDtoResponse {
    private Long id;
    private String descricao;
    private String codBarras;
    private BigDecimal valorUnitario;
    private BigDecimal quantidadeEstoque;
    private BigDecimal valorCusto;
}
