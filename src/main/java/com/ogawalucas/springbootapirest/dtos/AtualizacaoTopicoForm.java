package com.ogawalucas.springbootapirest.dtos;

import com.ogawalucas.springbootapirest.models.Topico;
import com.ogawalucas.springbootapirest.repositories.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class AtualizacaoTopicoForm {

    @NotBlank
    @Length(min = 5)
    private String titulo;

    @NotBlank
    @Length(min = 10)
    private String mensagem;

    public Topico atualizar(Long id, TopicoRepository repository) {
        Topico topico = repository.getById(id);

        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return topico;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
