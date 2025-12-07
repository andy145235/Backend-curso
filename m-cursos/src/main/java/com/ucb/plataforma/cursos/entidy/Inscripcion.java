package com.ucb.plataforma.cursos.entidy;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inscripcion")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInscripcion;

    @Column(name = "usuario_id", nullable = false)
    private String usuarioId; // El ID que viene de Keycloak (ej: "c67e1e9b...")

    @Column(name = "nombre_usuario")
    private String nombreUsuario; // Guardamos el nombre para mostrarlo fácil en la lista (ej: "andy")

    @Column(name = "fecha_inscripcion")
    private LocalDateTime fechaInscripcion = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    // Constructores, Getters y Setters
    public Inscripcion() {}

    public Inscripcion(String usuarioId, String nombreUsuario, Curso curso) {
        this.usuarioId = usuarioId;
        this.nombreUsuario = nombreUsuario;
        this.curso = curso;
    }

    public Long getIdInscripcion() { return idInscripcion; }
    public void setIdInscripcion(Long idInscripcion) { this.idInscripcion = idInscripcion; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public LocalDateTime getFechaInscripcion() { return fechaInscripcion; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
}