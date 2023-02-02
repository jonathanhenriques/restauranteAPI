package com.jonathanhenriques.domain.repository;

import com.jonathanhenriques.domain.model.Estado;
import com.jonathanhenriques.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

	
}