package com.example.poke_api.model;

import lombok.Data;
import java.util.List;

@Data
public class PokemonResponse {
    private List<Pokemon> results;
}
