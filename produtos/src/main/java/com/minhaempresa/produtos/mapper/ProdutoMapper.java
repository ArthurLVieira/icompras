package com.minhaempresa.produtos.mapper;

import com.minhaempresa.produtos.dto.request.ProdutoDtoRequest;
import com.minhaempresa.produtos.dto.response.ProdutoDtoResponse;
import com.minhaempresa.produtos.entity.Produto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    Produto toEntity(ProdutoDtoRequest request);
    ProdutoDtoResponse toDto(Produto produto);
}
