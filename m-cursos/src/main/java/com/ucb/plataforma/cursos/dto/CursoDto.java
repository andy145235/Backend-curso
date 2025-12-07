package com.ucb.plataforma.cursos.dto;

import java.time.LocalDateTime;
import java.util.List; // <--- IMPORTANTE

public class CursoDto {
    private Long idCurso;
    private String titulo;
    private String descripcion;
    private String categoria;
    private String nivel;
    private String estado;
    private LocalDateTime fechaCreacion;

    private Integer cuposMaximos;
    private Long inscritosActuales; // Campo calculado
    private boolean yaInscrito;
    private List<ModuloDto> modulos;

    private String instructor;


    // Getters y Setters
    public String getInstructor() {return instructor;}
    public void setInstructor(String instructor) {this.instructor = instructor;}
    public Integer getCuposMaximos() { return cuposMaximos; }
    public void setCuposMaximos(Integer cuposMaximos) { this.cuposMaximos = cuposMaximos; }
    public Long getInscritosActuales() { return inscritosActuales; }
    public void setInscritosActuales(Long inscritosActuales) { this.inscritosActuales = inscritosActuales; }
    public boolean isYaInscrito() { return yaInscrito; }
    public void setYaInscrito(boolean yaInscrito) { this.yaInscrito = yaInscrito; }
    // Getters y Setters
    public Long getIdCurso() { return idCurso; }
    public void setIdCurso(Long idCurso) { this.idCurso = idCurso; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    // 👇 GETTER Y SETTER DE MÓDULOS
    public List<ModuloDto> getModulos() { return modulos; }
    public void setModulos(List<ModuloDto> modulos) { this.modulos = modulos; }
}