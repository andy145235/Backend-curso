package com.ucb.plataforma.cursos.dto;

public class LeccionDto {

    // IMPORTANTE: Debe llamarse 'idLeccion' (camelCase) para coincidir con la Entidad
    private Long idLeccion;

    private String titulo;
    private String contenido;
    private Integer orden;
    private String videoUrl;
    private String pdfUrl;

    // --- GETTERS Y SETTERS ---

    public Long getIdLeccion() { return idLeccion; }
    public void setIdLeccion(Long idLeccion) { this.idLeccion = idLeccion; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public Integer getOrden() { return orden; }
    public void setOrden(Integer orden) { this.orden = orden; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public String getPdfUrl() { return pdfUrl; }
    public void setPdfUrl(String pdfUrl) { this.pdfUrl = pdfUrl; }
}