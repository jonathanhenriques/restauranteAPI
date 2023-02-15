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
import org.springframework.web.server.ResponseStatusException;

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
    public Cozinha buscarPorId(@PathVariable Long cozinhaID) {
        return cadastroCozinhaService.buscarOuFalhar(cozinhaID);
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
    public Cozinha atualizar(@PathVariable("id") Long id, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaEncontrada = cadastroCozinhaService.buscarOuFalhar(id);

            //classe do spring que faz o mapper de um obj para outro
            //"id" indica que será ignorado (o id aqui ele é null)
            //copiando os valores de COZINHA para COZINHAENCONTRADA, ignorando o ID
            BeanUtils.copyProperties(cozinha, cozinhaEncontrada, "id");

            return cadastroCozinhaService.salvar(cozinhaEncontrada);

    }

    /**
     * Em caso de SUCESSO
     * retorna @ResponseStatus(HttpStatus.NO_CONTENT)
     *
     * EM caso de ERRO
     * retorna o status referente ao erro
     * @param cozinhaId
     *
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable("id") Long cozinhaId) {
          cadastroCozinhaService.excluir(cozinhaId);
    }

}
