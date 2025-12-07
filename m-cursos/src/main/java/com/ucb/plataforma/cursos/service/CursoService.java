package com.ucb.plataforma.cursos.service;

import com.ucb.plataforma.cursos.dto.CursoDto;
import com.ucb.plataforma.cursos.dto.LeccionDto;
import com.ucb.plataforma.cursos.dto.ModuloDto;
import com.ucb.plataforma.cursos.entidy.Avance;
import com.ucb.plataforma.cursos.entidy.Curso;
import com.ucb.plataforma.cursos.entidy.Inscripcion;
import com.ucb.plataforma.cursos.entidy.Leccion;
import com.ucb.plataforma.cursos.entidy.Modulo;
import com.ucb.plataforma.cursos.mapper.CursoMapper;
import com.ucb.plataforma.cursos.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final ModuloRepository moduloRepository;
    private final LeccionRepository leccionRepository;
    private final InscripcionRepository inscripcionRepository;
    private final AvanceRepository avanceRepository;
    private final CursoMapper cursoMapper;
    private final CursoCriteriaService criteriaService;

    public CursoService(CursoRepository cursoRepository, ModuloRepository moduloRepository, LeccionRepository leccionRepository, InscripcionRepository inscripcionRepository, AvanceRepository avanceRepository, CursoMapper cursoMapper, CursoCriteriaService criteriaService) {
        this.cursoRepository = cursoRepository;
        this.moduloRepository = moduloRepository;
        this.leccionRepository = leccionRepository;
        this.inscripcionRepository = inscripcionRepository;
        this.avanceRepository = avanceRepository;
        this.cursoMapper = cursoMapper;
        this.criteriaService = criteriaService;
    }

    // --- MÉTODO PRINCIPAL (AQUÍ ESTÁ LA CORRECCIÓN) ---
    public List<CursoDto> obtenerTodosConCupos(String usuarioIdActual, boolean isAdmin) {
        // 1. Ordenar por ID para que no salten
        List<Curso> cursos = cursoRepository.findAll(Sort.by(Sort.Direction.ASC, "idCurso"));

        return cursos.stream()
                .filter(c -> isAdmin || "Activo".equals(c.getEstado()))
                .map(c -> {
                    CursoDto dto = cursoMapper.entityToDto(c);

                    // Datos calculados
                    dto.setInscritosActuales(inscripcionRepository.countByCursoIdCurso(c.getIdCurso()));
                    if(dto.getCuposMaximos() == null) dto.setCuposMaximos(20);
                    dto.setYaInscrito(usuarioIdActual != null && inscripcionRepository.existsByCursoIdCursoAndUsuarioId(c.getIdCurso(), usuarioIdActual));

                    // 👇👇👇 AQUÍ FALTA ESTE BLOQUE EN TU CÓDIGO 👇👇👇
                    // 2. 🚨 PARCHE DE FUERZA BRUTA: COPIAR IDs MANUALMENTE
                    if (c.getModulos() != null && dto.getModulos() != null) {
                        for (int i = 0; i < c.getModulos().size(); i++) {
                            if (i < dto.getModulos().size()) {
                                Modulo mEntidad = c.getModulos().get(i);
                                ModuloDto mDto = dto.getModulos().get(i);

                                // Copiar ID Módulo
                                mDto.setIdModulo(mEntidad.getIdModulo());

                                if (mEntidad.getLecciones() != null && mDto.getLecciones() != null) {
                                    for (int j = 0; j < mEntidad.getLecciones().size(); j++) {
                                        if (j < mDto.getLecciones().size()) {
                                            Leccion lEntidad = mEntidad.getLecciones().get(j);
                                            LeccionDto lDto = mDto.getLecciones().get(j);

                                            // ¡ESTA LÍNEA ES LA QUE ARREGLA EL ERROR!
                                            lDto.setIdLeccion(lEntidad.getIdLeccion());

                                            lDto.setVideoUrl(lEntidad.getVideoUrl());
                                            lDto.setPdfUrl(lEntidad.getPdfUrl());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    // 👆👆👆 FIN DEL PARCHE 👆👆👆

                    return dto;
                }).collect(Collectors.toList());
    }

    // --- OTROS MÉTODOS ---

    public List<CursoDto> obtenerTodos() { return obtenerTodosConCupos(null, true); }

    public CursoDto obtenerPorId(Long id) {
        return cursoMapper.entityToDto(cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("No encontrado")));
    }

    @Transactional
    public CursoDto crearCurso(CursoDto dto) {
        Curso c = cursoMapper.dtoToEntity(dto);
        if(c.getFechaCreacion()==null) c.setFechaCreacion(LocalDateTime.now());
        if(c.getCuposMaximos()==null) c.setCuposMaximos(20);
        c.setInstructor(dto.getInstructor());
        return cursoMapper.entityToDto(cursoRepository.save(c));
    }

    @Transactional
    public CursoDto updateCurso(Long id, CursoDto dto) {
        Curso c = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
        c.setTitulo(dto.getTitulo());
        c.setDescripcion(dto.getDescripcion());
        c.setCategoria(dto.getCategoria());
        c.setNivel(dto.getNivel());
        c.setEstado(dto.getEstado());
        c.setInstructor(dto.getInstructor());
        if(dto.getCuposMaximos()!=null) c.setCuposMaximos(dto.getCuposMaximos());

        Curso guardado = cursoRepository.save(c);
        CursoDto res = cursoMapper.entityToDto(guardado);
        res.setInscritosActuales(inscripcionRepository.countByCursoIdCurso(id));
        return res;
    }

    @Transactional
    public void deleteCurso(Long id) {
        if(!cursoRepository.existsById(id)) throw new RuntimeException("No encontrado");
        cursoRepository.deleteById(id);
    }

    @Transactional
    public CursoDto cambiarCupos(Long id, int cantidad) {
        Curso c = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
        int actual = c.getCuposMaximos() == null ? 20 : c.getCuposMaximos();
        c.setCuposMaximos(Math.max(0, actual + cantidad));
        return cursoMapper.entityToDto(cursoRepository.save(c));
    }

    // --- MÓDULOS Y LECCIONES ---
    @Transactional
    public ModuloDto agregarModulo(Long cursoId, ModuloDto dto) {
        Curso c = cursoRepository.findById(cursoId).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        Modulo m = new Modulo();
        m.setTitulo(dto.getTitulo());
        m.setDescripcion(dto.getDescripcion());
        m.setOrden(dto.getOrden());
        m.setCurso(c);
        return cursoMapper.moduloToDto(moduloRepository.save(m));
    }

    @Transactional
    public LeccionDto agregarLeccion(Integer moduloId, LeccionDto dto) {
        Modulo m = moduloRepository.findById(moduloId).orElseThrow(() -> new RuntimeException("Módulo no encontrado"));
        Leccion l = new Leccion();
        l.setTitulo(dto.getTitulo());
        l.setContenido(dto.getContenido());
        l.setOrden(dto.getOrden());
        l.setVideoUrl(dto.getVideoUrl());
        l.setPdfUrl(dto.getPdfUrl());
        l.setModulo(m);
        return cursoMapper.leccionToDto(leccionRepository.save(l));
    }

    @Transactional
    public ModuloDto updateModulo(Integer id, ModuloDto dto) {
        Modulo m = moduloRepository.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
        m.setTitulo(dto.getTitulo());
        m.setDescripcion(dto.getDescripcion());
        if(dto.getOrden()!=null) m.setOrden(dto.getOrden());
        return cursoMapper.moduloToDto(moduloRepository.save(m));
    }

    @Transactional
    public void deleteModulo(Integer id) {
        if(!moduloRepository.existsById(id)) throw new RuntimeException("No encontrado");
        moduloRepository.deleteById(id);
    }

    // --- INSCRIPCIÓN / PROGRESO ---
    @Transactional
    public void inscribirUsuario(Long cursoId, String usuarioId, String nombreUsuario) {
        Curso c = cursoRepository.findById(cursoId).orElseThrow(() -> new RuntimeException("No encontrado"));
        long n = inscripcionRepository.countByCursoIdCurso(cursoId);
        int max = c.getCuposMaximos() != null ? c.getCuposMaximos() : 20;
        if (n >= max) throw new RuntimeException("Lleno");
        if (inscripcionRepository.existsByCursoIdCursoAndUsuarioId(cursoId, usuarioId)) throw new RuntimeException("Ya inscrito");
        inscripcionRepository.save(new Inscripcion(usuarioId, nombreUsuario, c));
    }

    @Transactional
    public void desinscribirUsuario(Long cursoId, String usuarioId) {
        Inscripcion i = inscripcionRepository.findByCursoIdCursoAndUsuarioId(cursoId, usuarioId).orElseThrow(() -> new RuntimeException("No inscrito"));
        inscripcionRepository.delete(i);
    }

    @Transactional
    public void marcarLeccionComoVista(String userId, Long cursoId, Long leccionId) {
        if (!avanceRepository.existsByUsuarioIdAndLeccionId(userId, leccionId)) {
            avanceRepository.save(new Avance(userId, cursoId, leccionId));
        }
    }

    public List<Long> obtenerLeccionesVistas(String userId, Long cursoId) {
        return avanceRepository.findByUsuarioIdAndCursoId(userId, cursoId)
                .stream().map(Avance::getLeccionId).collect(Collectors.toList());
    }

    // --- BÚSQUEDA (Con parche también) ---
    public List<CursoDto> buscarConCriteria(String t, String e, String n) {
        List<Curso> entidades = criteriaService.buscarPorTituloYEstado(t, e, n);
        return entidades.stream().map(c -> {
            CursoDto dto = cursoMapper.entityToDto(c);
            dto.setInscritosActuales(inscripcionRepository.countByCursoIdCurso(c.getIdCurso()));
            if(dto.getCuposMaximos() == null) dto.setCuposMaximos(20);
            dto.setYaInscrito(false);

            // REPETIMOS EL PARCHE PARA BÚSQUEDA
            if (c.getModulos() != null && dto.getModulos() != null) {
                for (int i = 0; i < c.getModulos().size(); i++) {
                    if (i < dto.getModulos().size()) {
                        var mEnt = c.getModulos().get(i);
                        var mDto = dto.getModulos().get(i);
                        mDto.setIdModulo(mEnt.getIdModulo());
                        if(mEnt.getLecciones()!=null && mDto.getLecciones()!=null) {
                            for(int j=0; j<mEnt.getLecciones().size(); j++) {
                                if(j < mDto.getLecciones().size()) {
                                    var lEnt = mEnt.getLecciones().get(j);
                                    var lDto = mDto.getLecciones().get(j);
                                    lDto.setIdLeccion(lEnt.getIdLeccion()); // FORCE
                                    lDto.setVideoUrl(lEnt.getVideoUrl());
                                    lDto.setPdfUrl(lEnt.getPdfUrl());
                                }
                            }
                        }
                    }
                }
            }
            return dto;
        }).collect(Collectors.toList());
    }

    // ... dentro de CursoService ...

    @Transactional
    public void deleteLeccion(Integer idLeccion) {
        // Convertimos a Long si tu ID es Long, o Integer si es Integer
        Long idLong = Long.valueOf(idLeccion);

        if (!leccionRepository.existsById(Math.toIntExact(idLong))) {
            throw new RuntimeException("Lección no encontrada");
        }
        leccionRepository.deleteById(Math.toIntExact(idLong));
    }

    public List<Curso> obtenerPorTitulo(String t) { return cursoRepository.findByTitulo(t); }
    public List<Curso> obtenerPorNivelYEstado(String n, String e) { return cursoRepository.findByNivelAndEstado(n, e); }
    public List<Curso> obtenerPorEstado(String e) { return cursoRepository.buscarPorEstado(e); }
    public List<Curso> obtenerPorNivelNative(String n) { return cursoRepository.buscarPorNivelNative(n); }
}