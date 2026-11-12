package com.minhaempresa.produtos.repository;

import com.minhaempresa.produtos.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("""
        SELECT p FROM Produto p
        WHERE p.descricao = :descricao
        AND p.codBarras = :codBarras
    """)
    Optional<Produto> findByDescricaoAndCodBarras(
            @Param("descricao") String descricao,
            @Param("codBarras") String codBarras);

    @Query("""
        SELECT p FROM Produto p
        WHERE p.codBarras = :codBarras
    """)
    Optional<Produto> findByCodBarras(
            @Param("codBarras") String codBarras);
}
