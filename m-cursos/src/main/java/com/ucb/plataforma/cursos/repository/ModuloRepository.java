package com.ucb.plataforma.cursos.repository;

import com.ucb.plataforma.cursos.entidy.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Integer> {
}