package com.example.poke_api.controller;

import com.example.poke_api.exception.ValidationException;
import com.example.poke_api.model.PokemonResponse;
import com.example.poke_api.service.PokemonService;
import com.example.poke_api.validation.PokemonRequestValidator;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    //public static final Logger logger = (Logger) LoggerFactory.getLogger(PokemonController.class);

    private final PokemonService pokemonService;
    private final PokemonRequestValidator validate;


    public PokemonController(PokemonService pokemonService, PokemonRequestValidator validate) {
        this.pokemonService = pokemonService;
        this.validate = validate;
    }

    @GetMapping("/pokename")
    public ResponseEntity<PokemonResponse> getPokemons(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "20") int size) {
        try {
            validate.validatePagination(page, size);
            return pokemonService.getPokemons(page, size);
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
