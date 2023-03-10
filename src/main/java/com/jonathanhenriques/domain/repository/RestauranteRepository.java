package com.jonathanhenriques.domain.repository;

import com.jonathanhenriques.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository
        extends
        //CustomJpaRepository ja herda JpaRepository
        CustomJpaRepository<Restaurante, Long>,
//        JpaRepository<Restaurante, Long>,
        IRestauranteRepositoryQueries,
        JpaSpecificationExecutor<Restaurante> {

    /**@query para definirmos a consulta sql e minimizar as consultas ao banco feitas pelo hibernate (n+1) */
//    @Query("from Restaurante r join r.cozinha left join fetch r.formasPagamento")
    @Query("from Restaurante r join r.cozinha")
    List<Restaurante> findAll();

    List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    //	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
    List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);

    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    List<Restaurante> findTop2ByNomeContaining(String nome);

    int countByCozinhaId(Long cozinha);

}
