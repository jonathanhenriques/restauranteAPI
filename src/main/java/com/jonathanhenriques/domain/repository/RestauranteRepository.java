package com.jonathanhenriques.domain.repository;

import com.jonathanhenriques.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

	List<Restaurante> listar();
	Restaurante buscar(Long id);
	Restaurante salvar(Restaurante restaurante);
	void remover(Restaurante restaurante);
	
}