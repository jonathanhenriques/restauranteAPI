package com.jonathanhenriques.domain.repository;

import com.jonathanhenriques.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository {

	List<Cozinha> listar();
	List<Cozinha> consultarPorNome(String nome);
	Cozinha buscar(Long id);


	Cozinha salvar(Cozinha cozinha);
	void remover(Long id);

}
