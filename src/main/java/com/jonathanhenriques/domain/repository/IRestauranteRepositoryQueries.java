package com.jonathanhenriques.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.jonathanhenriques.domain.model.Restaurante;

public interface IRestauranteRepositoryQueries {
    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
//
//    List<Restaurante> findRestaurantes(String nome);
//
//    List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
//
//    Optional<Restaurante> consultarPorNome(String nome, Long cozinhaId);
}
