package com.ogawalucas.springbootapirest.controllers;

import com.ogawalucas.springbootapirest.dtos.TopicoDto;
import com.ogawalucas.springbootapirest.dtos.TopicoForm;
import com.ogawalucas.springbootapirest.models.Topico;
import com.ogawalucas.springbootapirest.repositories.CursoRepository;
import com.ogawalucas.springbootapirest.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("topicos")
    public List<TopicoDto> lista(String cursoNome) {
        if (cursoNome == null) {
            return TopicoDto.converter(repository.findAll());
        } else {
            return TopicoDto.converter(repository.findByCursoNome(cursoNome));
        }
    }

    @PostMapping
    public void cadastrar(@RequestBody TopicoForm form) {
        Topico topico = form.converter(cursoRepository);
        repository.save(topico);
    }
}