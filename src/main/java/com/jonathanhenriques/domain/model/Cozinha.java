package com.jonathanhenriques.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.*;
import lombok.*;

import javax.persistence.*;

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

}