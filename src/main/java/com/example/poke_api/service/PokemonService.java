package com.example.poke_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class PokemonService {

    @Value("${pokeapi.url}")
    private String pokeApiUrl;

    private final RestTemplate restTemplate;

    public PokemonService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> getPokemons(int page, int size){
       try {
           int offset = page * size;
           String apiUrl = pokeApiUrl + "/pokemon-species?offset=" + offset + "&limit=" + size;
           return restTemplate.getForEntity(apiUrl, String.class);
       }catch (RestClientException e){
           throw new RuntimeException("Error al obtener la data");
       }
    }
}
