package com.jonathanhenriques.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
//@JsonRootName("gastronomia") //muda nome da classe no xml de retorno
public class Cozinha {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@JsonIgnore
//	@JsonProperty(value = "titulo") //muda o nome da propriedade na serializacao
	@Column(nullable = false)
	private String nome;

	@JsonIgnore
	@OneToMany(mappedBy = "cozinha") //dono do mapeamento feito do outro lado da associacao
	private List<Restaurante> restaurantes = new ArrayList<>();

}