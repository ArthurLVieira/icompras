package com.minhaempresa.produtos.service.validator;

import com.minhaempresa.produtos.entity.Produto;
import com.minhaempresa.produtos.exception.BusinessException;
import com.minhaempresa.produtos.exception.enuns.ErrorCode;
import com.minhaempresa.produtos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProdutoValidator {
    private final ProdutoRepository repository;

    public Produto validateEntityId(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new BusinessException(ErrorCode.PRODUTO_NOT_FOUND));
    }

    public void validateEntity(Produto  entity, Long id) {
        if (existsEntity(entity, id)) {
            throw new BusinessException(ErrorCode.PRODUTO_ALREADY_EXISTS);
        }

        if (existsEntity(entity, id)) {
            throw new BusinessException(ErrorCode.PRODUTO_ALREADY_EXISTS);
        }
    }

    private boolean existsEntity(Produto entity, Long id) {
        Optional<Produto> aux = repository.findByDescricaoAndCodBarras(
                entity.getDescricao(),
                entity.getCodBarras());

        if (id == null) {
            return aux.isPresent();
        }

        return aux.isPresent() && !aux.get().getId().equals(id);
    }

    private boolean existsEntityByCodBarras(Produto entity, Long id) {
        Optional<Produto> aux = repository.findByCodBarras(entity.getCodBarras());

        if (id == null) {
            return aux.isPresent();
        }

        return aux.isPresent() && !aux.get().getId().equals(id);
    }

    public Produto validateSaidaEstoque(BigDecimal qtdSaida, Long id) {
        Produto entity = validateEntityId(id);

        if (entity.getQuantidadeEstoque().compareTo(qtdSaida) < 0) {
            throw new BusinessException(ErrorCode.PRODUTO_UNAVAILABLE_ESTOQUE);
        }

        if (entity.getQuantidadeEstoque().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(ErrorCode.PRODUTO_ZERO_BALANCE);
        }

        entity.setQuantidadeEstoque(entity.getQuantidadeEstoque().subtract(qtdSaida));
        return entity;
    }
}
