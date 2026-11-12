package com.minhaempresa.clientes.repository;

import com.minhaempresa.clientes.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);

    Optional<Cliente> findByNomeAndCpf(String nome, String cpf);
}
