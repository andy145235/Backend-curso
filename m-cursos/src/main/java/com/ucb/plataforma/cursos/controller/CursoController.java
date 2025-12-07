package com.ucb.plataforma.cursos.controller;

import com.ucb.plataforma.cursos.dto.CursoDto;
import com.ucb.plataforma.cursos.dto.ModuloDto;
import com.ucb.plataforma.cursos.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Cursos", description = "Operaciones CRUD para la gestión de cursos")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // 1. GET ALL

    @Operation(summary = "Obtener todos los cursos")
    @GetMapping
    public List<CursoDto> getAllCursos(JwtAuthenticationToken token) {
        String userId = null;
        boolean isAdmin = false;

        if (token != null) {
            userId = token.getName(); // ID del usuario

            // Verificamos si tiene el rol de admin
            // (Ajusta "realm_access" o el claim según tu configuración de Keycloak,
            // pero Spring Security suele mapearlo a Authorities como "ROLE_admin")
            isAdmin = token.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equalsIgnoreCase("ROLE_admin")
                            || a.getAuthority().equalsIgnoreCase("admin"));
        }

        return cursoService.obtenerTodosConCupos(userId, isAdmin);
    }

    // 2. GET BY ID
    @Operation(summary = "Obtener curso por ID")
    @GetMapping("/{id}")
    public CursoDto getCursoById(@PathVariable Long id) {
        return cursoService.obtenerPorId(id);
    }

    // 3. CREATE
    @Operation(summary = "Crear un curso")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CursoDto createCurso(@Valid @RequestBody CursoDto cursoDto) {
        return cursoService.crearCurso(cursoDto);
    }

    // 4. UPDATE (¡Solo debe haber uno de estos!)
    @Operation(summary = "Actualizar curso existente")
    @PutMapping("/{id}")
    public CursoDto updateCurso(@PathVariable Long id, @Valid @RequestBody CursoDto cursoDto) {
        return cursoService.updateCurso(id, cursoDto);
    }

    // 5. DELETE
    @Operation(summary = "Eliminar curso")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
    }

    // --- MÉTODOS DE BÚSQUEDA ---



    // ENDPOINT DE INSCRIPCIÓN
    @PostMapping("/{id}/inscribir")
    @ResponseStatus(HttpStatus.OK)
    public void inscribirse(@PathVariable Long id, JwtAuthenticationToken token) {
        if (token == null) throw new RuntimeException("Debes estar logueado");

        String userId = token.getName(); // ID único (sub)
        String username = (String) token.getTokenAttributes().get("preferred_username"); // Nombre visible (andy)

        cursoService.inscribirUsuario(id, userId, username);
    }


    @GetMapping("/derived/titulo/{titulo}")
    public List<?> derivedByTitulo(@PathVariable String titulo) {
        return cursoService.obtenerPorTitulo(titulo);
    }

    @GetMapping("/derived/nivel-estado")
    public List<?> derivedByNivelYEstado(@RequestParam String nivel, @RequestParam String estado) {
        return cursoService.obtenerPorNivelYEstado(nivel, estado);
    }

    @GetMapping("/jpql/estado/{estado}")
    public List<?> jpqlPorEstado(@PathVariable String estado) {
        return cursoService.obtenerPorEstado(estado);
    }

    @GetMapping("/native/nivel/{nivel}")
    public List<?> nativePorNivel(@PathVariable String nivel) {
        return cursoService.obtenerPorNivelNative(nivel);
    }

    @GetMapping("/criteria")
    public List<CursoDto> criteria(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String nivel) {
        return cursoService.buscarConCriteria(titulo, estado, nivel);
    }
    // --- ESTE ERA EL QUE FALTABA ---
    @PostMapping("/{cursoId}/modulos")
    @ResponseStatus(HttpStatus.CREATED)
    public ModuloDto addModulo(@PathVariable Long cursoId, @RequestBody ModuloDto moduloDto) {
        return cursoService.agregarModulo(cursoId, moduloDto);
    }

    @PatchMapping("/{id}/cupos")
    public CursoDto actualizarCupos(@PathVariable Long id, @RequestParam int cantidad) {
        return cursoService.cambiarCupos(id, cantidad);
    }

    // ... (otros endpoints) ...


    // DESINSCRIBIRSE
    @DeleteMapping("/{id}/inscribir")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desinscribirse(@PathVariable Long id, JwtAuthenticationToken token) {
        if (token == null) throw new RuntimeException("Debes estar logueado");
        String userId = token.getName();

        // Llamamos al servicio para borrar la inscripción
        cursoService.desinscribirUsuario(id, userId); // <--- Asegúrate de tener este método en el servicio también
    }
    // 1. Marcar lección como vista
    @PostMapping("/{cursoId}/lecciones/{leccionId}/completar")
    @ResponseStatus(HttpStatus.OK)
    public void completarLeccion(@PathVariable Long cursoId, @PathVariable Long leccionId, JwtAuthenticationToken token) {
        if (token == null) throw new RuntimeException("Debes estar logueado");
        cursoService.marcarLeccionComoVista(token.getName(), cursoId, leccionId);
    }

    // 2. Obtener progreso (IDs de lecciones vistas)
    // ESTE ES EL QUE TE DA ERROR 404 AHORA MISMO
    @GetMapping("/{cursoId}/progreso")
    public List<Long> obtenerProgreso(@PathVariable Long cursoId, JwtAuthenticationToken token) {
        if (token == null) return List.of(); // Si no está logueado, devolvemos lista vacía
        return cursoService.obtenerLeccionesVistas(token.getName(), cursoId);
    }

}