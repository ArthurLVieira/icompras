package com.minhaempresa.pedidos.mapper;

import com.minhaempresa.pedidos.dto.request.PedidoDtoRequest;
import com.minhaempresa.pedidos.dto.response.PedidoDtoResponse;
import com.minhaempresa.pedidos.entity.Pedido;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ItemPedidoOfPedidoMapper.class})
public interface PedidoMapper {
    @Mappings({
            @Mapping(target = "total", ignore = true),
            @Mapping(target = "dataAtualizacao", ignore = true),
            @Mapping(target = "chavePagamento", ignore = true),
            @Mapping(target = "dataCadastro", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "status", ignore = true),
    })
    Pedido toEntity(PedidoDtoRequest dto);

    @Mappings({
            @Mapping(target = "total", source = "total"),
            @Mapping(target = "chavePagamento", source = "chavePagamento"),
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "status", source = "status")
    })
    PedidoDtoResponse toDto(Pedido pedido);

    @AfterMapping
    default void afterMapping(@MappingTarget Pedido entity) {
        if (entity.getItens() != null) {
            entity.getItens().forEach(item -> {
                item.setPedido(entity);
            });
        }
    }
}
