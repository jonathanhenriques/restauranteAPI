package com.jonathanhenriques.infra.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.jonathanhenriques.domain.repository.RestauranteRepository;
import com.jonathanhenriques.infra.specification.RestauranteSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jonathanhenriques.domain.model.Restaurante;
import com.jonathanhenriques.domain.repository.IRestauranteRepositoryQueries;

/**
 * Classe de implementacao de RestauranteRepository
 *
 * Classe criada para fazer a ligação dos métodos de consulta criados
 * "manualmente" usando JPQL ou SQL nativo com a interface RestauranteRepository
 *
 * OBRIGATORIO o uso de RepositoryImpl no nome
 */
@Repository
public class RestauranteRepositoryImpl implements IRestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager entityManager;

	@Lazy //para resolver problema de referencia circular
	//injecao do Proprio restauranteRepository para ter acesso aos metodos da JpaREpository
	@Autowired
	private RestauranteRepository restauranteRepository;

	/**
	 * Consulta equivalente a
	 * SELECT * FROM Restaurante
	 * @param nome
	 * @return List<Restaurante> restaurantes
	 */
	public List<Restaurante> findRestaurantes(String nome) {

		/**
		 * Esse bloco equivale a from Restaurante
		 * ----------------------------------------------------------------------------------
		 */
		CriteriaBuilder builder = entityManager.getCriteriaBuilder(); // constroi o criteria
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class); // prepara a query
		Root<Restaurante> root = criteria.from(Restaurante.class); // representa a query # from Restaurante # seria a
																	// origem ROOT
		/**
		 * *
		 * ----------------------------------------------------------------------------
		 */

		TypedQuery<Restaurante> query = entityManager.createQuery(criteria);
		return query.getResultList();
	}

	/**
	 * VERSÃO USANDO CRITERIA API
	 * 
	 * Método responsável por fazer busca 
	 * de restaurantes por nome, taxaFrete inicial e final
	 *
	 * Equivalente a
	 * SELEXT * FROM Restaurante
	 * Where Restaurante.nome Like %nome%
	 * AND Restaurante.taxaInicial >= taxaFreteInicial
	 * AND Restaurante.taxaFreteFinal <= taxaFreteFInal
	 */
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){

		/**
		 * Esse bloco equivale a from Restaurante
		 * ----------------------------------------------------------------------------------
		 */
		CriteriaBuilder builder = entityManager.getCriteriaBuilder(); // constroi o criteria
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class); // prepara a query
		Root<Restaurante> root = criteria.from(Restaurante.class); // representa a query # from Restaurante # seria a origem ROOT da query
		/**
		 * ----------------------------------------------------------------------------
		 */
		
		List<Predicate> predicates = new ArrayList<>(); //lista de Predicates que representam a consulta
		
		//caso os parametros tenham sido passados, eles sao adicionados a consulta, query
		if(StringUtils.hasText(nome)) {
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%")); //criando predicate que funciona como um filtro de where
		}
		
		if(taxaFreteInicial != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
		}
		
		
		if(taxaFreteFinal != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
		}
		/*predicates.toArray(new Predicate[0]) -> uma das formas de converter uma colecao em array é 
		* usando toArray() e passar a instancia de um array vazio
		*/
		criteria.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Restaurante> query = entityManager.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<Restaurante> fincComFreteGratis(String nome) {
//		return restauranteRepository.findAll(RestauranteSpecification.comFreteGratis());
//		return restauranteRepository.findAll(RestauranteSpecification.ComNomeSemelhante(nome));
		return restauranteRepository.findAll(RestauranteSpecification.comFreteGratis().and(RestauranteSpecification.ComNomeSemelhante(nome)));
	}


	/**
	 * 
	 * VERSÃO USANDO SPQL
	 * 
	 * Método responsável por fazer busca dinâmica usando SPQL
	 * passando três parâmetros opcionais
	 */
//    @Override
//    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
//
//    	HashMap<String, Object> parametros = new HashMap<String, Object>();
//        var jpql = new StringBuilder();
//        jpql.append("from Restaurante where 0 = 0 "); //where 0 = 0 usado para garantir que retorne pelo menos algo
//        
//        if(org.springframework.util.StringUtils.hasLength(nome)) { //StringUtils.hasLength verifica se não está null e nem vazio
//        	jpql.append(" and nome like :nome ");
//        	parametros.put("nome", "%" + nome + "%");
//        }
//        if(taxaFreteInicial != null) {
//        	jpql.append(" and taxaFrete >= :taxaInicial ");
//        	parametros.put("taxaInicial", taxaFreteInicial);
//        }
//        if(taxaFreteFinal != null) {
//        	jpql.append(" and taxaFrete <= :taxaFinal ");
//        	parametros.put("taxaInicial", taxaFreteFinal);
//        }
//        
//        
//
//        TypedQuery<Restaurante> query =  entityManager.createQuery(jpql.toString(), Restaurante.class);
//        		
//        parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
//        		
////                .setParameter("nome", "%" + nome + "%")
////                .setParameter("taxaInicial", taxaFreteInicial)
////                .setParameter("taxaFinal", taxaFreteFinal)
//               return query.getResultList();
//    }
}
