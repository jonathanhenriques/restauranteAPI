package com.jonathanhenriques.domain.repository;


import com.jonathanhenriques.domain.model.FormaPagamento;
import com.jonathanhenriques.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

	
}
