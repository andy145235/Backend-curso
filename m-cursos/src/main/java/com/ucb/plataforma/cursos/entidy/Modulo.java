package com.ucb.plataforma.cursos.entidy; // ⚠️ Corrige el nombre del paquete (era "entidy")

import com.ucb.plataforma.cursos.entidy.Curso;
import com.ucb.plataforma.cursos.entidy.Leccion;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "modulo") // ⚠️ Asegúrate de que coincida con tu tabla real (puede ser "modulo" o "modulos")
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modulo")
    private Integer idModulo; // ⚠️ Mejor usar Integer (permite null antes de guardar)

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private Integer orden;

    // --- Relación con Curso ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false) // ⚠️ Evita repetir "id_curso" si ya existe en Curso
    private Curso curso;

    // --- Relación con Lección ---
    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Leccion> lecciones;

    // --- CONSTRUCTORES ---
    public Modulo() {
    }

    public Modulo(String titulo, String descripcion, Integer orden, Curso curso) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.orden = orden;
        this.curso = curso;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Leccion> getLecciones() {
        return lecciones;
    }

    public void setLecciones(List<Leccion> lecciones) {
        this.lecciones = lecciones;
    }


}