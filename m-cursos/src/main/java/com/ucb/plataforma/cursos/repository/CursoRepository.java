package com.ucb.plataforma.cursos.repository;

import com.ucb.plataforma.cursos.entidy.Curso; // Corregido: 'entity' y no 'entidy'
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    // Consulta opcional por idCurso
    @Transactional(readOnly = true)
    List<Curso> findByIdCurso(Integer idCurso);

    // Derived query
    List<Curso> findByTitulo(String titulo);

    List<Curso> findByNivelAndEstado(String nivel, String estado);

    // JPQL query
    @Query("SELECT c FROM Curso c WHERE c.estado = :estado")
    List<Curso> buscarPorEstado(String estado);

    // Native query
    @Query(value = "SELECT * FROM curso WHERE nivel = :nivel", nativeQuery = true)
    List<Curso> buscarPorNivelNative(String nivel);

    @Query("SELECT c FROM Curso c WHERE c.nivel = :nivel")
    List<Curso> buscarPorNivel(@Param("nivel") String nivel);
    
}
