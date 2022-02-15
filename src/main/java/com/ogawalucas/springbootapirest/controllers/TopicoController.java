package com.ogawalucas.springbootapirest.controllers;

import com.ogawalucas.springbootapirest.dtos.TopicoDto;
import com.ogawalucas.springbootapirest.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @RequestMapping("topicos")
    public List<TopicoDto> lista(String cursoNome) {
        if (cursoNome == null) {
            return TopicoDto.converter(repository.findAll());
        } else {
            return TopicoDto.converter(repository.findByCursoNome(cursoNome));
        }
    }
}