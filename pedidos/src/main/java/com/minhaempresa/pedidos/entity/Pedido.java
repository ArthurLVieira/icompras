package com.minhaempresa.pedidos.entity;

import com.minhaempresa.pedidos.entity.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "pedido", schema = "public")
public class Pedido implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "total", precision = 15, scale = 2)
    private BigDecimal total = new  BigDecimal(0);

    @Column(name = "observacoes")
    private String observacao;

    @Column(name = "status")
    private String status;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DadosPagamento> dadosPagamento;

    @Column(name = "data_cadastro")
    @CreatedDate
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao")
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;


    public void setStatus(StatusPedido statusPedido) {
        this.status = StatusPedido.valueOfStatus(statusPedido);
    }

    public String getStatus() {
        return StatusPedido.valueOfStringForString(status);
    }
}
