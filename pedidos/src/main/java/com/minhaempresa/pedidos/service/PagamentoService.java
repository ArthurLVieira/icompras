package com.minhaempresa.pedidos.service;

import com.minhaempresa.pedidos.dto.request.PagamentoDtoRequest;
import com.minhaempresa.pedidos.entity.enums.StatusPedido;
import com.minhaempresa.pedidos.exception.BusinessException;
import com.minhaempresa.pedidos.exception.enuns.ErrorCode;
import com.minhaempresa.pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    private final PedidoRepository repository;

    public void updataPagamento(PagamentoDtoRequest pagamento) {
        var pedido = repository.findByIdAndChavePagamento(
                pagamento.getIdPedido(),
                pagamento.getChavePagamento()
        ).orElseThrow(() -> new BusinessException(ErrorCode.PEDIDO_NOT_FOUND));

        pedido.setChavePagamento(pagamento.getChavePagamento());
        pedido.setObservacao(pagamento.getObservacao());

        if (!pagamento.getStatus()) {
            pedido.setStatus(StatusPedido.ERRO_PAGAMENTO);
            pedido.setObservacao("Erro pagamento");
        } else {
            pedido.setStatus(StatusPedido.FATURADO);
            pedido.setObservacao("Pagamento Realizado.");
        }

        repository.save(pedido);
    }
}
