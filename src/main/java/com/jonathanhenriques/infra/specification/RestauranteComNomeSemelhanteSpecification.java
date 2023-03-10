package com.jonathanhenriques.infra.specification;

import com.jonathanhenriques.domain.model.Restaurante;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Classe que representa o
 * Predicate comNomeSemelhante
 * para pesquisa na JpaSpecification
 *
 * Obrigatorio implementar
 * o metodo toPredicate()
 */
@AllArgsConstructor
public class RestauranteComNomeSemelhanteSpecification implements Specification<Restaurante> {

    private String nome;


    @Override
    public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }
}
