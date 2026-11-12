package com.minhaempresa.clientes.service.validator;

import com.minhaempresa.clientes.entity.Cliente;
import com.minhaempresa.clientes.exception.BusinessException;
import com.minhaempresa.clientes.exception.enuns.ErrorCode;
import com.minhaempresa.clientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClienteValidator {
    private final ClienteRepository repository;

    public Cliente validatorEntityById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new BusinessException(ErrorCode.CLIENTE_NOT_FOUND));
    }

    public Cliente validatorEntityByCPF(String cpf) {
        return repository.findByCpf(cpf).orElseThrow(() ->
                new BusinessException(ErrorCode.CLIENTE_NOT_FOUND_BY_CPF));
    }

    public void validateEntity(Cliente entity, Long id) {
        if (existsEntity(entity, id)) {
            throw new BusinessException(ErrorCode.CLIENTE_ALREADY_EXISTS);
        }

        if (existsEntityByCpf(entity, id)) {
            throw new BusinessException(ErrorCode.CLIENTE_ALREADY_EXISTS_BY_CPF);
        }
    }

    private boolean existsEntity(Cliente entity, Long id) {
        Optional<Cliente> aux = repository.findByNomeAndCpf(
                entity.getNome(), entity.getCpf()
        );

        if (id == null) {
            return aux.isPresent();
        }

        return aux.isPresent() && !id.equals(aux.get().getId());
    }

    private boolean existsEntityByCpf(Cliente entity,  Long id) {
        Optional<Cliente> aux = repository.findByCpf(entity.getCpf());

        if (id == null) {
            return aux.isPresent();
        }

        return aux.isPresent() && id.equals(aux.get().getId());
    }
}
