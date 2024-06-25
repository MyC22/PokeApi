package com.example.poke_api.controller;

import com.example.poke_api.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class PokemonController {

    private final PokemonService pokemonService;


    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    public ResponseEntity<String> getPokemons(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size){
        try {
            return pokemonService.getPokemons(page,size);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los pokemons");
        }
    }
}
