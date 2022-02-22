package com.ogawalucas.springbootapirest.dtos;

import com.ogawalucas.springbootapirest.models.Curso;
import com.ogawalucas.springbootapirest.models.Topico;
import com.ogawalucas.springbootapirest.repositories.CursoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class TopicoForm {

    @NotBlank @Length(min = 5)
    private String titulo;

    @NotBlank @Length(min = 10)
    private String mensagem;

    @NotBlank @Length
    private String nomeCurso;

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(this.nomeCurso);
        return new Topico(this.titulo, this.mensagem, curso);
    }
}
