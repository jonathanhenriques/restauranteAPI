package com.jonathanhenriques.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND
//        , reason = "Entidade não encontrada!" //passar uma mensagem por padrão
//)
public class EntidadeNaoEncontradaException extends ResponseStatusException {

    public EntidadeNaoEncontradaException(HttpStatus status, String mensagem) {
        super(status, mensagem);
    }

    /**
     * Esse coonstrutor chama o de cima
     * passando POR PADRAO
     * o status NOT_FOUND
     * @param mensagem
     */
    public EntidadeNaoEncontradaException(Object mensagem){
       this(HttpStatus.NOT_FOUND, mensagem.toString());
    }
}
