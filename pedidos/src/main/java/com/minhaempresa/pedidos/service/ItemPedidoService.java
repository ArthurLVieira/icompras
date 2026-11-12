package com.minhaempresa.pedidos.service;

import com.minhaempresa.pedidos.dto.request.ItemPedidoDtoRequest;
import com.minhaempresa.pedidos.dto.response.ItemPedidoDtoResponse;
import com.minhaempresa.pedidos.entity.ItemPedido;
import com.minhaempresa.pedidos.mapper.ItemPedidoMapper;
import com.minhaempresa.pedidos.repository.ItemPedidoRepository;
import com.minhaempresa.pedidos.service.validator.ItemPedidoValidator;
import com.minhaempresa.pedidos.service.validator.PedidoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemPedidoService {
    private final ItemPedidoRepository itemPedidoRepository;
    private final ItemPedidoValidator itemPedidoValidator;
    private final ItemPedidoMapper itemPedidoMapper;
    private final PedidoValidator pedidoValidator;

    public ItemPedidoDtoResponse findById(Long id) {
        return itemPedidoMapper.toDto(itemPedidoValidator.validateEntityId(id));
    }

    public Page<ItemPedidoDtoResponse> findByPedidoId(Long pedidoId, Pageable pageable) {
        pedidoValidator.validateEntityId(pedidoId);
        Page<ItemPedido> page = itemPedidoRepository.findByPedidoId(pedidoId, pageable);
        return page.map(itemPedidoMapper::toDto);
    }

    public ItemPedidoDtoResponse insert(ItemPedidoDtoRequest dto) {
        ItemPedido itemPedido = itemPedidoMapper.toEntity(dto);
        itemPedidoValidator.validateEntity(itemPedido);
        return itemPedidoMapper.toDto(itemPedidoRepository.save(itemPedido));
    }

    public ItemPedidoDtoResponse update(ItemPedidoDtoRequest dto, Long id) {
        ItemPedido newEntity = itemPedidoMapper.toEntity(dto);
        ItemPedido currentEntity = itemPedidoValidator.validateEntityId(id);
        itemPedidoValidator.updateData(newEntity, currentEntity);
        return itemPedidoMapper.toDto(itemPedidoRepository.save(currentEntity));
    }
}
