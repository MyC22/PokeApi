package com.example.poke_api.validation;

import com.example.poke_api.exception.ValidationException;

public class PokemonRequestValidator {

    public void validatePagination(int page, int size){
        if (page < 0){
            throw new ValidationException("El numero de la pagina debe ser igual a 0 o mayor");
        }
        if (size <= 0){
            throw new ValidationException("El tamaño de la pagina debe ser mayor que 0");
        }
    }
}
