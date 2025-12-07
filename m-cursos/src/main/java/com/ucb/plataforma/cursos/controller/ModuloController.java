package com.ucb.plataforma.cursos.controller;

import com.ucb.plataforma.cursos.dto.LeccionDto;
import com.ucb.plataforma.cursos.dto.ModuloDto;
import com.ucb.plataforma.cursos.repository.LeccionRepository;
import com.ucb.plataforma.cursos.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modulos")
@Tag(name = "Módulos", description = "Gestión de módulos y lecciones")
public class ModuloController {

    @Autowired
    private CursoService cursoService;

    // Endpoint: POST /modulos/{id}/lecciones
    @Operation(summary = "Agregar una lección a un módulo")
    @PostMapping("/{moduloId}/lecciones")
    @ResponseStatus(HttpStatus.CREATED)
    public LeccionDto addLeccion(@PathVariable Integer moduloId, @RequestBody LeccionDto leccionDto) {
        return cursoService.agregarLeccion(moduloId, leccionDto);
    }
    // EDITAR MÓDULO
    @Operation(summary = "Actualizar un módulo")
    @PutMapping("/{id}")
    public ModuloDto updateModulo(@PathVariable Integer id, @RequestBody ModuloDto dto) {
        return cursoService.updateModulo(id, dto);
    }

    // ELIMINAR MÓDULO
    @Operation(summary = "Eliminar un módulo")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteModulo(@PathVariable Integer id) {
        cursoService.deleteModulo(id);
    }

    @Autowired
    private LeccionRepository leccionRepository; // Asegúrate de inyectarlo

    @DeleteMapping("/lecciones/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLeccion(@PathVariable Long id) {
        if (!leccionRepository.existsById(Math.toIntExact(id))) {
            throw new RuntimeException("Lección no encontrada");
        }
        leccionRepository.deleteById(Math.toIntExact(id));
    }



}