package com.jonathanhenriques.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException{

    public EntidadeNaoEncontradaException(Object mensagem){
        super(mensagem.toString());
    }
}
