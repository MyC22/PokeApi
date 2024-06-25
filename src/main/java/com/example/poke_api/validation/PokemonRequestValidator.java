package com.example.poke_api.validation;

import com.example.poke_api.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class PokemonRequestValidator {

    public void validatePagination(int page, int size){
        if (page < 0){
            throw new ValidationException("El numero de la pagina debe ser mayor ogual a 0");
        }
        if (size <= 0){
            throw new ValidationException("El tamaño de la pagina debe ser mayor que 0");
        }
    }
}
