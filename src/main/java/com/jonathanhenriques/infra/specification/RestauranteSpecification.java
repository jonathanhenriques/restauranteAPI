package com.jonathanhenriques.infra.specification;

import com.jonathanhenriques.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

/**
 * Classe responsavel por ser
 * a fabrica de specifications
 */
public class RestauranteSpecification {

    public static Specification<Restaurante> comFreteGratis() {
        return new RestauranteComFreteGratisSpecification();
    }

    public static Specification<Restaurante> comFreteGratis(String nome) {
        return new RestauranteComNomeSemelhanteSpecification(nome);
    }


}
