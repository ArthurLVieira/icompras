package com.minhaempresa.pedidos.mapper;

import com.minhaempresa.pedidos.dto.request.ItemPedidoDtoRequest;
import com.minhaempresa.pedidos.dto.response.ItemPedidoDtoResponse;
import com.minhaempresa.pedidos.entity.ItemPedido;
import com.minhaempresa.pedidos.service.validator.PedidoValidator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ItemPedidoMapper {

    @Autowired
    protected PedidoValidator pedidoValidator;

    @Mapping(source = "pedidoId", target = "pedido.id")
    public abstract ItemPedido toEntity(ItemPedidoDtoRequest dto);

    @Mapping(target = "pedido",
            expression = "java(pedidoValidator.validateEntityId(itemPedido.getPedido().getId()))")
    public abstract ItemPedidoDtoResponse toDto(ItemPedido itemPedido);
}
