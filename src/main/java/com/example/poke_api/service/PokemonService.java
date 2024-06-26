package com.example.poke_api.service;
import com.example.poke_api.client.PokemonClient;
import com.example.poke_api.exception.ServiceException;
import com.example.poke_api.model.PokemonResponse;
import com.example.poke_api.validation.PokemonRequestValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
public class PokemonService {

    private final PokemonClient pokemonClient;
    private final PokemonRequestValidator validate;

    public PokemonService(PokemonClient pokemonClient, PokemonRequestValidator validate){
        this.pokemonClient = pokemonClient;
        this.validate = validate;
    }

    public ResponseEntity<PokemonResponse> getPokemons(int page, int size){
        validate.validatePagination(page,size);
        try {
            int offset = page * size;
            return pokemonClient.getPokemons(offset, size);
        } catch (RestClientException e) {
            throw new ServiceException("Error al obtener la data", e);
        }
    }
}
