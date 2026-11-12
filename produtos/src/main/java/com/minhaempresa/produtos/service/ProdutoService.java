package com.minhaempresa.produtos.service;

import com.minhaempresa.produtos.dto.request.ProdutoDtoRequest;
import com.minhaempresa.produtos.dto.response.ProdutoDtoResponse;
import com.minhaempresa.produtos.entity.Produto;
import com.minhaempresa.produtos.mapper.ProdutoMapper;
import com.minhaempresa.produtos.repository.ProdutoRepository;
import com.minhaempresa.produtos.service.validator.ProdutoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;
    private final ProdutoValidator validator;

    public ProdutoDtoResponse findById(Long id) {
        return mapper.toDto(validator.validateEntityId(id));
    }

    public ProdutoDtoResponse insert(ProdutoDtoRequest dto) {
        Produto entity = mapper.toEntity(dto);
        validator.validateEntity(entity, null);
        return mapper.toDto(repository.save(entity));
    }

    public ProdutoDtoResponse updateSaidaEstoque(BigDecimal qtdSaida, Long id) {
        Produto entity = validator.validateSaidaEstoque(qtdSaida, id);
        return mapper.toDto(repository.save(entity));
    }

    public void atualizaEstoque(BigDecimal estoque, Long id) {
        Produto entity = validator.validateEntityId(id);
        entity.setQuantidadeEstoque(entity.getQuantidadeEstoque().add(estoque));
        repository.save(entity);
    }
}
