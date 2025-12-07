package com.ucb.plataforma.cursos.entidy;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "avance")
public class Avance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvance;

    @Column(name = "usuario_id")
    private String usuarioId;

    @Column(name = "curso_id")
    private Long cursoId;

    @Column(name = "leccion_id")
    private Long leccionId;

    private LocalDateTime fechaFin = LocalDateTime.now();

    // --- CONSTRUCTORES ---
    public Avance() {}

    public Avance(String usuarioId, Long cursoId, Long leccionId) {
        this.usuarioId = usuarioId;
        this.cursoId = cursoId;
        this.leccionId = leccionId;
    }

    // --- GETTERS Y SETTERS (Necesarios aunque salgan en gris) ---

    public Long getIdAvance() { return idAvance; }
    public void setIdAvance(Long idAvance) { this.idAvance = idAvance; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public Long getCursoId() { return cursoId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }

    public Long getLeccionId() { return leccionId; }
    public void setLeccionId(Long leccionId) { this.leccionId = leccionId; }

    public LocalDateTime getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }
}