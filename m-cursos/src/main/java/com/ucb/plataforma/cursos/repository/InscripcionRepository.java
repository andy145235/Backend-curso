package com.ucb.plataforma.cursos.repository;

import com.ucb.plataforma.cursos.entidy.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    // Para saber si un usuario ya está inscrito en un curso específico
    boolean existsByCursoIdCursoAndUsuarioId(Long cursoId, String usuarioId);

    // Para contar cuántos inscritos hay en un curso
    long countByCursoIdCurso(Long cursoId);

    // Para listar quiénes están en un curso
    Optional<Inscripcion> findByCursoIdCursoAndUsuarioId(Long cursoId, String usuarioId);
}