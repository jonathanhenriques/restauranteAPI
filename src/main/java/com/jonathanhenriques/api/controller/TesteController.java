package com.jonathanhenriques.api.controller;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.jonathanhenriques.infra.specification.RestauranteComFreteGratisSpecification;
import com.jonathanhenriques.infra.specification.RestauranteComNomeSemelhanteSpecification;
import com.jonathanhenriques.infra.specification.RestauranteSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonathanhenriques.domain.model.Cozinha;
import com.jonathanhenriques.domain.model.Restaurante;
import com.jonathanhenriques.domain.repository.CozinhaRepository;
import com.jonathanhenriques.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(String nome) {
		return cozinhaRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	@GetMapping("/cozinhas/unica-por-nome")
	public Optional<Cozinha> cozinhaPorNome(String nome) {
		return cozinhaRepository.findTop1ByNomeContainingIgnoreCase(nome);
	}
	
	@GetMapping("/cozinhas/exists")
	public boolean cozinhaExists(String nome) {
		return cozinhaRepository.existsByNome(nome);
	}

	@GetMapping("/cozinhas/primeiro")
	public Optional<Cozinha> cozinhasComFreteGratis( ) {
		/**Método que vem de CustomJpaRepository */
		return cozinhaRepository.buscarPrimeiro();
	}

	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(
			BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.queryByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> restaurantesPorTaxaFrete(
			String nome, Long cozinhaId) {
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
	}
	
	@GetMapping("/restaurantes/primeiro-por-nome")
	public Optional<Restaurante> restaurantePrimeiroPorNome(String nome) {
		return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
	}
	
	@GetMapping("/restaurantes/top2-por-nome")
	public List<Restaurante> restaurantesTop2PorNome(String nome) {
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}
	
	@GetMapping("/restaurantes/por-nome-e-frete")
	public List<Restaurante> restaurantesPorNomeFrete(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("/restaurantes/count-por-cozinha")
	public int restaurantesCountPorCozinha(Long cozinhaId) {
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}

	/**
	 * Endpoint que consome Specification
	 * faz um filtro e retorna Restaurantes
	 * com frete grátis e by nome semelhante
	 * em uma mesma chamada
	 * @param nome
	 * @return List<Restaurante>
	 */
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome) {

	return restauranteRepository.fincComFreteGratis(nome);
	}


	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> restaurantesComFreteGratis( ) {
		/**Método que vem de CustomJpaRepository */
		return restauranteRepository.buscarPrimeiro();
	}



	
}
