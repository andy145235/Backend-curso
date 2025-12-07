package com.ucb.plataforma.cursos.dto;
import java.util.List;

public class ModuloDto {
    private Integer idModulo;
    private String titulo;
    private String descripcion;
    private Integer orden;
    private List<LeccionDto> lecciones; // Lista anidada

    // Getters y Setters
    public Integer getIdModulo() { return idModulo; }
    public void setIdModulo(Integer idModulo) { this.idModulo = idModulo; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Integer getOrden() { return orden; }
    public void setOrden(Integer orden) { this.orden = orden; }
    public List<LeccionDto> getLecciones() { return lecciones; }
    public void setLecciones(List<LeccionDto> lecciones) { this.lecciones = lecciones; }
}