package com.ogawalucas.springbootapirest.repositories;

import com.ogawalucas.springbootapirest.models.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findByCursoNome(String cursoNome, PageRequest of);
}
