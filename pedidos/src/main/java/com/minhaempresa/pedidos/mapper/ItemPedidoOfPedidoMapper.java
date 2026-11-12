package com.minhaempresa.pedidos.mapper;

import com.minhaempresa.pedidos.dto.request.ItemPedidoDtoRequest;
import com.minhaempresa.pedidos.dto.response.ItemPedidoDtoResponse;
import com.minhaempresa.pedidos.entity.ItemPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemPedidoOfPedidoMapper {
    @Mapping(target = "pedido", ignore = true)
    ItemPedido toEntity(ItemPedidoDtoRequest dto);

    @Mapping(target = "pedido", ignore = true)
    ItemPedidoDtoResponse toDto(ItemPedido itemPedido);

    List<ItemPedido> toEntityList(List<ItemPedidoDtoRequest> dto);
    List<ItemPedidoDtoResponse> toDtoList(List<ItemPedido> itemPedidos);
}