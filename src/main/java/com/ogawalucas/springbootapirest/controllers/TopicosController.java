package com.ogawalucas.springbootapirest.controllers;

import com.ogawalucas.springbootapirest.dtos.TopicoDto;
import com.ogawalucas.springbootapirest.models.Curso;
import com.ogawalucas.springbootapirest.models.Topico;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicosController {

    @RequestMapping("topicos")
    public List<TopicoDto> lista() {
        Topico topico = new Topico("Duvida", "Duvida com Spring", new Curso("Spring", "Programção"));

        return TopicoDto.converter(Arrays.asList(topico, topico, topico));
    }
}