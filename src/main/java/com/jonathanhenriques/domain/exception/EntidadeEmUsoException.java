package com.jonathanhenriques.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.CONFLICT) //mesma coisa
@ResponseStatus(code = HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends RuntimeException{
    public EntidadeEmUsoException(Object mensagem){
        super(mensagem.toString());
    }
}
