package com.jonathanhenriques.infra.specification;

import com.jonathanhenriques.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

/**
 * Classe responsavel por ser
 * a fabrica de specifications
 * que retorna instancias de specifications
 */
public class RestauranteSpecification {

    public static Specification<Restaurante> comFreteGratis() {
        /** Retornando uma instancia, é necessario criar uma classe para cada specification*/
//        return new RestauranteComFreteGratisSpecification();

        /** Retornando diretamente nao é necessario criar uma classe para cada specification*/
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> ComNomeSemelhante(String nome) {
        return new RestauranteComNomeSemelhanteSpecification(nome);

        /** Retornando diretamente nao é necessario criar uma classe para cada specification*/
//        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }


}
