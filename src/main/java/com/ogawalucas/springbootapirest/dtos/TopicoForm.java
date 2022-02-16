package com.ogawalucas.springbootapirest.dtos;

import com.ogawalucas.springbootapirest.models.Curso;
import com.ogawalucas.springbootapirest.models.Topico;
import com.ogawalucas.springbootapirest.repositories.CursoRepository;

public class TopicoForm {

    private String titulo;
    private String mensagem;
    private String nomeCurso;

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(this.nomeCurso);
        return new Topico(this.titulo, this.mensagem, curso);
    }
}
