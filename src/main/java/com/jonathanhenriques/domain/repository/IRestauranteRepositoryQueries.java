package com.jonathanhenriques.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.jonathanhenriques.domain.model.Restaurante;

/**
 * Interface para declarar os
 * metodos personalizados
 * que estao no xml(orm.xml) ou specifications
 *
 * e nao sao da JpaREpository
 * mas que sera implementada na interface RestauranteRepository
 */
public interface IRestauranteRepositoryQueries {
    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

    List<Restaurante> fincComFreteGratis(String nome);

}
