package com.example.poke_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PokeConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
