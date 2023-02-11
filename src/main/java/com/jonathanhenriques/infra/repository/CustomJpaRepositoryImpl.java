package com.jonathanhenriques.infra.repository;

import com.jonathanhenriques.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
implements CustomJpaRepository<T, ID>{

    private EntityManager entityManager;

    /**
     * Construtor que recebe os mesmos parametros
     * da classe PAI SimpleJpaRepository
     * e repaça no super()
     * @param entityInformation
     * @param entityManager
     */
    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    /**
     * Implementacao do metodo buscarPrimeiro
     * de CustomJpaRepository
     * @return
     */
    @Override
    public Optional<T> buscarPrimeiro() {

        /**SimpleJpaRepository.getDomainClass retorna o TIPO da classe utilizada*/
        var jpql = "from " + getDomainClass().getName();  //equivalente a "FROM Restaurante" exemplo
        T entity = entityManager.createQuery(jpql, getDomainClass())
                .setMaxResults(1) //retorna o PRIMEIRO resultado
                .getSingleResult();

        return Optional.ofNullable(entity);
    }
}
