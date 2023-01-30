package com.jonathanhenriques.domain.exception;

public class EntidadeEmUsoException extends RuntimeException{
    public EntidadeEmUsoException(Object mensagem){
        super(mensagem.toString());
    }
}
