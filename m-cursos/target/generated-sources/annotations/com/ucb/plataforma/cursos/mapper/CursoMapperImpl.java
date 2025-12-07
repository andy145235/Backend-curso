package com.ucb.plataforma.cursos.mapper;

import com.ucb.plataforma.cursos.dto.CursoDto;
import com.ucb.plataforma.cursos.dto.LeccionDto;
import com.ucb.plataforma.cursos.dto.ModuloDto;
import com.ucb.plataforma.cursos.entidy.Curso;
import com.ucb.plataforma.cursos.entidy.Leccion;
import com.ucb.plataforma.cursos.entidy.Modulo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-06T12:27:15-0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23-valhalla (Oracle Corporation)"
)
@Component
public class CursoMapperImpl implements CursoMapper {

    @Override
    public CursoDto entityToDto(Curso entity) {
        if ( entity == null ) {
            return null;
        }

        CursoDto cursoDto = new CursoDto();

        cursoDto.setInstructor( entity.getInstructor() );
        cursoDto.setCuposMaximos( entity.getCuposMaximos() );
        cursoDto.setIdCurso( entity.getIdCurso() );
        cursoDto.setTitulo( entity.getTitulo() );
        cursoDto.setDescripcion( entity.getDescripcion() );
        cursoDto.setCategoria( entity.getCategoria() );
        cursoDto.setNivel( entity.getNivel() );
        cursoDto.setEstado( entity.getEstado() );
        cursoDto.setFechaCreacion( entity.getFechaCreacion() );
        cursoDto.setModulos( moduloListToModuloDtoList( entity.getModulos() ) );

        return cursoDto;
    }

    @Override
    public Curso dtoToEntity(CursoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Curso curso = new Curso();

        curso.setInstructor( dto.getInstructor() );
        curso.setCuposMaximos( dto.getCuposMaximos() );
        curso.setIdCurso( dto.getIdCurso() );
        curso.setTitulo( dto.getTitulo() );
        curso.setDescripcion( dto.getDescripcion() );
        curso.setCategoria( dto.getCategoria() );
        curso.setNivel( dto.getNivel() );
        curso.setEstado( dto.getEstado() );

        return curso;
    }

    @Override
    public ModuloDto moduloToDto(Modulo entity) {
        if ( entity == null ) {
            return null;
        }

        ModuloDto moduloDto = new ModuloDto();

        moduloDto.setIdModulo( entity.getIdModulo() );
        moduloDto.setTitulo( entity.getTitulo() );
        moduloDto.setDescripcion( entity.getDescripcion() );
        moduloDto.setOrden( entity.getOrden() );
        moduloDto.setLecciones( leccionListToLeccionDtoList( entity.getLecciones() ) );

        return moduloDto;
    }

    @Override
    public Modulo dtoToEntity(ModuloDto dto) {
        if ( dto == null ) {
            return null;
        }

        Modulo modulo = new Modulo();

        modulo.setIdModulo( dto.getIdModulo() );
        modulo.setTitulo( dto.getTitulo() );
        modulo.setDescripcion( dto.getDescripcion() );
        modulo.setOrden( dto.getOrden() );
        modulo.setLecciones( leccionDtoListToLeccionList( dto.getLecciones() ) );

        return modulo;
    }

    @Override
    public Leccion dtoToEntity(LeccionDto dto) {
        if ( dto == null ) {
            return null;
        }

        Leccion leccion = new Leccion();

        leccion.setIdLeccion( dto.getIdLeccion() );
        leccion.setTitulo( dto.getTitulo() );
        leccion.setContenido( dto.getContenido() );
        leccion.setOrden( dto.getOrden() );
        leccion.setVideoUrl( dto.getVideoUrl() );
        leccion.setPdfUrl( dto.getPdfUrl() );

        return leccion;
    }

    protected List<ModuloDto> moduloListToModuloDtoList(List<Modulo> list) {
        if ( list == null ) {
            return null;
        }

        List<ModuloDto> list1 = new ArrayList<ModuloDto>( list.size() );
        for ( Modulo modulo : list ) {
            list1.add( moduloToDto( modulo ) );
        }

        return list1;
    }

    protected List<LeccionDto> leccionListToLeccionDtoList(List<Leccion> list) {
        if ( list == null ) {
            return null;
        }

        List<LeccionDto> list1 = new ArrayList<LeccionDto>( list.size() );
        for ( Leccion leccion : list ) {
            list1.add( leccionToDto( leccion ) );
        }

        return list1;
    }

    protected List<Leccion> leccionDtoListToLeccionList(List<LeccionDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Leccion> list1 = new ArrayList<Leccion>( list.size() );
        for ( LeccionDto leccionDto : list ) {
            list1.add( dtoToEntity( leccionDto ) );
        }

        return list1;
    }
}
