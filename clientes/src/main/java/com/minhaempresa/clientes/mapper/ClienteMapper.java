package com.minhaempresa.clientes.mapper;

import com.minhaempresa.clientes.dto.request.ClienteDtoRequest;
import com.minhaempresa.clientes.dto.response.ClienteDtoResponse;
import com.minhaempresa.clientes.entity.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toEntity(ClienteDtoRequest request);
    ClienteDtoResponse toDto(Cliente cliente);
}
