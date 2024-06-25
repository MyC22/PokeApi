package com.example.poke_api.service;
import com.example.poke_api.client.PokemonClient;
import com.example.poke_api.exception.ServiceException;
import com.example.poke_api.model.PokemonResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class PokemonService {

    private final PokemonClient pokemonClient;

    public PokemonService(PokemonClient pokemonClient){
        this.pokemonClient = pokemonClient;
    }

    public ResponseEntity<PokemonResponse> getPokemons(int page, int size){
        try {
            int offset = page * size;
            return pokemonClient.getPokemons(offset, size);
        } catch (RestClientException e) {
            throw new ServiceException("Error al obtener la data", e);
        }
    }
}
