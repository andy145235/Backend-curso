package com.ucb.plataforma.cursos.repository;

import com.ucb.plataforma.cursos.entidy.Leccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeccionRepository extends JpaRepository<Leccion, Integer> {
}