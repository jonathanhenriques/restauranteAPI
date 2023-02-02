package com.jonathanhenriques.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jonathanhenriques.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{

	List<Cozinha> findByNomeContainingIgnoreCase(@Param("nome")String nome);
	
	Optional<Cozinha> findTop1ByNomeContainingIgnoreCase(@Param("nome")String nome);
	
	boolean existsByNome(@Param("nome") String nome);

}
