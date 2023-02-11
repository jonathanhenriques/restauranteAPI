package com.jonathanhenriques.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean //sinaliza que esta interface nao deve ser instanciada pelo container do Spring e sera ignorada
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {

    Optional<T> buscarPrimeiro();


}
