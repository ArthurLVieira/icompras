package com.minhaempresa.pedidos.service;

import com.minhaempresa.pedidos.client.ClienteClient;
import com.minhaempresa.pedidos.client.ServicoBancarioClient;
import com.minhaempresa.pedidos.client.representation.ClienteRepresentation;
import com.minhaempresa.pedidos.dto.ApiResponse;
import com.minhaempresa.pedidos.dto.request.PedidoDtoRequest;
import com.minhaempresa.pedidos.dto.response.PedidoDtoResponse;
import com.minhaempresa.pedidos.entity.ItemPedido;
import com.minhaempresa.pedidos.entity.Pedido;
import com.minhaempresa.pedidos.mapper.PedidoMapper;
import com.minhaempresa.pedidos.repository.PedidoRepository;
import com.minhaempresa.pedidos.service.validator.PedidoValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PedidoValidator pedidoValidator;
    private final PedidoMapper pedidoMapper;
    private final ServicoBancarioClient servicoBancarioClient;
    private final ClienteClient clienteClient;

    public PedidoDtoResponse findById(Long id) {
        return pedidoMapper.toDto(pedidoValidator.validateEntityId(id));
    }

    @Transactional
    public PedidoDtoResponse insert(PedidoDtoRequest dto) {
        Pedido entity = pedidoMapper.toEntity(dto);
        entity.setChavePagamento(servicoBancarioClient.solicitarPagamento(entity));
        entity.setDataPedido(LocalDateTime.now());
        pedidoValidator.validateEntity(entity, null);
        pedidoValidator.calcularTotalItensPedido(entity);
        pedidoValidator.calcularTotalPedido(entity);
        entity = pedidoRepository.save(entity);
        pedidoValidator.baixaEstoquePedido(entity);

        return pedidoMapper.toDto(entity);
    }

    @Transactional
    public PedidoDtoResponse update(PedidoDtoRequest dto, Long id) {
        Pedido newEntity = pedidoMapper.toEntity(dto);
        Pedido existingEntity = pedidoValidator.validateEntityId(id);

        sincronizarItens(existingEntity, newEntity.getItens());
        pedidoValidator.updateData(newEntity, existingEntity);

        return pedidoMapper.toDto(pedidoRepository.save(existingEntity));
    }

    public ClienteRepresentation findClienteById(Long id) {
        ApiResponse<ClienteRepresentation> response = clienteClient.findById(id);

        if (response == null || !response.isSuccess()) {
            throw new RuntimeException("Falha ao buscar cliente: "
                    + (response != null ? response.getMessage() : "sem resposta"));
        }
        return response.getData();
    }

    private void sincronizarItens(Pedido existingEntity, List<ItemPedido> newItens) {
        if (existingEntity.getItens() == null) {
            existingEntity.setItens(new ArrayList<>());
        }

        if (newItens == null) {
            return; // ou lance exceção, conforme a regra de negócio
        }

        Map<Long, ItemPedido> novosPorProduto = newItens.stream()
                .filter(item -> item.getProdutoId() != null)
                .collect(Collectors.toMap(
                        ItemPedido::getProdutoId,
                        Function.identity(),
                        (a, b) -> a // ou lance exceção se duplicado
                ));

        // Remove os que não estão mais no novo pedido
        existingEntity.getItens().removeIf(item -> {
            boolean remover = !novosPorProduto.containsKey(item.getProdutoId());

            // Se NÃO usar orphanRemoval, delete explicitamente antes:
            // if (remover) {
            //     itemPedidoRepository.delete(item);
            // }

            return remover;
        });

        // Adiciona ou atualiza
        for (ItemPedido novoItem : novosPorProduto.values()) {
            ItemPedido itemExistente = existingEntity.getItens().stream()
                    .filter(item -> Objects.equals(item.getProdutoId(), novoItem.getProdutoId()))
                    .findFirst()
                    .orElse(null);

            if (itemExistente == null) {
                ItemPedido item = ItemPedido.builder()
                        .pedido(existingEntity)
                        .produtoId(novoItem.getProdutoId())
                        .quantidade(novoItem.getQuantidade())
                        .valorUnitario(novoItem.getValorUnitario())
                        .build();

                existingEntity.getItens().add(item);
            } else {
                itemExistente.setQuantidade(novoItem.getQuantidade());
                itemExistente.setValorUnitario(novoItem.getValorUnitario());
                // Se quiser forçar atualização, mesmo sem mudança:
                itemExistente.setDataAtualizacao(LocalDateTime.now());
            }
        }
    }

}
