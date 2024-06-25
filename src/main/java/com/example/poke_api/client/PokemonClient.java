package com.example.poke_api.client;

import com.example.poke_api.model.PokemonResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class PokemonClient {
    @Value("${pokeapi.url}")
    private String pokeApiUrl;
    private final RestTemplate restTemplate;

    public PokemonClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<PokemonResponse> getPokemons(int offset, int limit){
            String apiUrl = pokeApiUrl + "?offset=" + offset + "&limit=" + limit;
            return restTemplate.getForEntity(apiUrl, PokemonResponse.class);
    }
}
