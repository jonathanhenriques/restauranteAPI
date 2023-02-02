package com.jonathanhenriques.domain.repository;

import com.jonathanhenriques.domain.model.Cidade;
import com.jonathanhenriques.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	
}