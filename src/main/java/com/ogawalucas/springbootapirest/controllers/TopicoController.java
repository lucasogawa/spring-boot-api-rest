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
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        repository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) {
        return repository.findById(id)
            .map(topico -> ResponseEntity.ok(new DetalhesDoTopicoDto(topico)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    @PutMapping("{id}")
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        return repository.findById(id)
            .map(topico -> ResponseEntity.ok(new TopicoDto(form.atualizar(topico))))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        var topico = repository.findById(id);

        if (topico.isPresent()) {
            repository.delete(topico.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}