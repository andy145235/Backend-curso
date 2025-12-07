package com.ucb.plataforma.cursos.mapper;

import com.ucb.plataforma.cursos.dto.*;
import com.ucb.plataforma.cursos.entidy.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    // --- CURSO ---
    @Mapping(target = "inscritosActuales", ignore = true)
    @Mapping(target = "yaInscrito", ignore = true)
    CursoDto entityToDto(Curso entity);

    @Mapping(target = "modulos", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    Curso dtoToEntity(CursoDto dto);

    // --- MÓDULO ---
    ModuloDto moduloToDto(Modulo entity);

    @Mapping(target = "curso", ignore = true)
    Modulo dtoToEntity(ModuloDto dto);

    // --- LECCIÓN (MAPEO MANUAL OBLIGATORIO) ---
    // Esto fuerza a Java a copiar el ID sí o sí.
    default LeccionDto leccionToDto(Leccion entity) {
        if ( entity == null ) return null;

        LeccionDto dto = new LeccionDto();

        // COPIA EXPLÍCITA DE DATOS
        dto.setIdLeccion( entity.getIdLeccion() ); // <--- AQUÍ ESTÁ LA CLAVE
        dto.setTitulo( entity.getTitulo() );
        dto.setContenido( entity.getContenido() );
        dto.setOrden( entity.getOrden() );
        dto.setVideoUrl( entity.getVideoUrl() );
        dto.setPdfUrl( entity.getPdfUrl() );

        return dto;
    }

    @Mapping(target = "modulo", ignore = true)
    Leccion dtoToEntity(LeccionDto dto);
}