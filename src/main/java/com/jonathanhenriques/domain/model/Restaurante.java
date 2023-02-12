package com.jonathanhenriques.domain.model;

//import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    /** O hibernate cria uma classe proxy pra cada
     * propriedade de relacionamento em modo Lazy
     * e isso pode gerar excessoes
     * "hibernateLazyInitializer" é o nome
     * dessa propriedade proxy
     * e ao ignorar, evitamos excessoes
      */
//    @JsonIgnoreProperties({"hibernateLazyInitializer"})

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false)
    /**
     * Restaurante é o dono do relacionamento com cozinha
     * e não necessita de tabela de associacao
     * pois restaurante já tem uma coluna com ids de cozinha
     * feita por @JoinColumn(name = "cozinha_id"
     */
    private Cozinha cozinha;

    @JsonIgnore
    @Embedded
    /**Embedded sinaliza que a classe foi incorporada a esta entidade */
    private Endereco endereco;

    @JsonIgnore
    @CreationTimestamp//atribui data de criacao
    /** columnDefinition = "datetime" indicando que sera um campo de data */
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp//atribui data de atualizacao
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos;

//    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
/**
 * @JoinTable cria uma nova tabela no relacionamento @ManyToMany
 * name = define o nome da nova tabela
 * @joinColumns = define o nome do parametro que será trazido para a nova tabela como chave estrangeira
 * @InverseJoinColumn = define o nome do parametro que será trazido para a nova tabela como chave estrangeira da outra tabela do relacionamento
 *
 */
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

}