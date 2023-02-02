package com.jonathanhenriques.domain.service;

import com.jonathanhenriques.domain.exception.EntidadeNaoEncontradaException;
import com.jonathanhenriques.domain.model.Cozinha;
import com.jonathanhenriques.domain.model.Restaurante;
import com.jonathanhenriques.domain.repository.CozinhaRepository;
import com.jonathanhenriques.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(cozinhaId+""));

        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
        }

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

}