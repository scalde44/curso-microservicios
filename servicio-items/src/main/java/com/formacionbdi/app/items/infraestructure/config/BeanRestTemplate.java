package com.formacionbdi.app.items.infraestructure.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanRestTemplate {
    @Bean(name = "clienteRest")
    @LoadBalanced
    public RestTemplate registrarRestTemplate() {
        return new RestTemplate();
    }
}
