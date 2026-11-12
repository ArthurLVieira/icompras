package com.minhaempresa.produtos.controller.generic;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public interface Generics {

    default URI getUri(String id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

}
