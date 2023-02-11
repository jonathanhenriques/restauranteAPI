package com.jonathanhenriques.api.controller;

import com.jonathanhenriques.domain.exception.EntidadeEmUsoException;
import com.jonathanhenriques.domain.exception.EntidadeNaoEncontradaException;
import com.jonathanhenriques.domain.model.Cozinha;
import com.jonathanhenriques.domain.model.Restaurante;
import com.jonathanhenriques.domain.repository.CozinhaRepository;
import com.jonathanhenriques.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;


    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cozinha> buscarPorId(@PathVariable Long id) {
        Cozinha cozinha = cozinhaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(id+""));
//Modificar no postman automatically follow redirects
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.LOCATION,
//				"http://api.algafood.local:8080/cozinhas"); //exemplo pra entendimento
//		return ResponseEntity.status(HttpStatus.FOUND)
//				.headers(headers)
//				.build();
        if (cozinha != null) {
            return ResponseEntity.ok(cozinha);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/primeiro")
    public Optional<Cozinha> CozinhasComFreteGratis( ) {
        /**Método que vem de CustomJpaRepository */
        return cozinhaRepository.buscarPrimeiro();
    }


    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        Cozinha cozinhaSalva = cadastroCozinhaService.salvar(cozinha);
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaSalva);

    }

    @PutMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cozinha> atualizar(@PathVariable("id") Long id, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaEncontrada = cozinhaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(id+""));

        if (cozinhaEncontrada != null) {
            //classe do spring que faz o mapper de um obj para outro
            //"id" indica que será ignorado (o id aqui ele é null)
            //copiando os valores de COZINHA para COZINHAENCONTRADA, ignorando o ID
            BeanUtils.copyProperties(cozinha, cozinhaEncontrada, "id");

            cozinhaEncontrada = cadastroCozinhaService.salvar(cozinhaEncontrada);
            return ResponseEntity.ok(cozinhaEncontrada);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") Long id) {
        try {
            cadastroCozinhaService.excluir(id);
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
