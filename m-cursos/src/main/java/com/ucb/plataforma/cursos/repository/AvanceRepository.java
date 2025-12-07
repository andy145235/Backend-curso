package com.ucb.plataforma.cursos.repository;

import com.ucb.plataforma.cursos.entidy.Avance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AvanceRepository extends JpaRepository<Avance, Long> {
    List<Avance> findByUsuarioIdAndCursoId(String usuarioId, Long cursoId);

    // CORREGIDO: Long leccionId
    boolean existsByUsuarioIdAndLeccionId(String usuarioId, Long leccionId);
}