package com.minhaempresa.pedidos.repository;

import com.minhaempresa.pedidos.entity.ItemPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    @Query("""
        SELECT it FROM ItemPedido it
        WHERE it.pedido.id = :pedidoId
    """)
    Page<ItemPedido> findByPedidoId(@Param("pedidoId") Long pedidoId, Pageable pageable);

    @Query("""
        DELETE ItemPedido it
        WHERE it.pedido.id = :pedidoId
        AND it.produtoId = :produtoId
    """)
    static void deleteByPedidoAndProdutoId(
            @Param("pedidoId") Long pedidoId,
            @Param("produtoId") Long produtoId) {
    }
}
