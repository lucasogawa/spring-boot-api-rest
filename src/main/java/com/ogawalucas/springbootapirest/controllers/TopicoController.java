package com.ogawalucas.springbootapirest.controllers;

import com.ogawalucas.springbootapirest.dtos.AtualizacaoTopicoForm;
import com.ogawalucas.springbootapirest.dtos.DetalhesDoTopicoDto;
import com.ogawalucas.springbootapirest.dtos.TopicoDto;
import com.ogawalucas.springbootapirest.dtos.TopicoForm;
import com.ogawalucas.springbootapirest.models.Topico;
import com.ogawalucas.springbootapirest.repositories.CursoRepository;
import com.ogawalucas.springbootapirest.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String cursoNome) {
        if (cursoNome == null) {
            return TopicoDto.converter(repository.findAll());
        } else {
            return TopicoDto.converter(repository.findByCursoNome(cursoNome));
        }
    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        repository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("{id}")
    public DetalhesDoTopicoDto detalhar(@PathVariable Long id) {
            return new DetalhesDoTopicoDto(repository.getById(id));
    }

    @Transactional
    @PutMapping("{id}")
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        Topico topico = form.atualizar(id, repository);

        return ResponseEntity.ok(new TopicoDto(topico));
    }
}