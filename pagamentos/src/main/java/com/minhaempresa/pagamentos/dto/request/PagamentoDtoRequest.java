package com.minhaempresa.pagamentos.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDtoRequest {
    private Long idPedido;
    private String chavePagamento;
    private String observacao;
    private Boolean status;
}
