package com.minhaempresa.clientes.service;

import com.minhaempresa.clientes.dto.request.ClienteDtoRequest;
import com.minhaempresa.clientes.dto.response.ClienteDtoResponse;
import com.minhaempresa.clientes.entity.Cliente;
import com.minhaempresa.clientes.mapper.ClienteMapper;
import com.minhaempresa.clientes.repository.ClienteRepository;
import com.minhaempresa.clientes.service.validator.ClienteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;
    private final ClienteMapper mapper;
    private final ClienteValidator validator;

    public ClienteDtoResponse findById(Long id) {
        return mapper.toDto(validator.validatorEntityById(id));
    }

    public ClienteDtoResponse findByCpf(String cpf) {
        return mapper.toDto(validator.validatorEntityByCPF(cpf));
    }

    public ClienteDtoResponse insert(ClienteDtoRequest request) {
        Cliente entity = mapper.toEntity(request);
        validator.validateEntity(entity, null);
        return mapper.toDto(repository.save(entity));
    }
}
