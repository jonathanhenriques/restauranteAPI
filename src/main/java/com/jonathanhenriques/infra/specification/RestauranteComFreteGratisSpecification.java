package com.jonathanhenriques.infra.specification;

import com.jonathanhenriques.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;


/**
 * Classe que representa o
 * Predicate ComFreteGratis
 * para pesquisa na JpaSpecification
 *
 * Obrigatorio implementar
 * o metodo toPredicate()
 *
 * CLASSE NAO UTLIZADA, MATIDA APENAS COMO REFERENCIA DE ESTUDOS
 */
public class RestauranteComFreteGratisSpecification implements Specification<Restaurante> {


    @Override
    public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }
}
