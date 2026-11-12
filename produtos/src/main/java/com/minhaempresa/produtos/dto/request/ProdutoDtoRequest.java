package com.minhaempresa.produtos.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDtoRequest {
    @NotNull(message = "Campo Descrição obrigatório.")
    private String descricao;
    private String codBarras;
    private BigDecimal valorUnitario;
    private BigDecimal quantidadeEstoque;
    private BigDecimal valorCusto;
}
