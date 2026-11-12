package com.minhaempresa.pedidos.entity;

import com.minhaempresa.pedidos.entity.enums.TipoPagamentoEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString()
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tipo_pagamento", schema = "public")
public class TipoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pagamento", nullable = false)
    private TipoPagamentoEnum tipoPagamento;
}
