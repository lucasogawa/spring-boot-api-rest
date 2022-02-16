package com.ogawalucas.springbootapirest.repositories;

import com.ogawalucas.springbootapirest.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Curso findByNome(String nome);
}
