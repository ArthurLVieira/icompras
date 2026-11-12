package com.minhaempresa.pedidos.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.minhaempresa.pedidos.client")
public class ClientsConfig {
}
