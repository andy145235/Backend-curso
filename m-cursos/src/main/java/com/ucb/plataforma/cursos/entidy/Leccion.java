package com.ucb.plataforma.cursos.entidy;

import jakarta.persistence.*;

@Entity
@Table(name = "lecciones")
public class Leccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_leccion") // En la base de datos se llama 'id_leccion'
    private Long idLeccion;      // En Java se llama 'idLeccion'

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private Integer orden;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "pdf_url")
    private String pdfUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modulo")
    private Modulo modulo;

    public Leccion() {}

    // --- GETTERS Y SETTERS ---
    // Importante: El getter debe llamarse getIdLeccion (Java estándar)
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

    public Modulo getModulo() { return modulo; }
    public void setModulo(Modulo modulo) { this.modulo = modulo; }
}