package com.ucb.plataforma.cursos.service;

import com.ucb.plataforma.cursos.entidy.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoCriteriaService {

    private final EntityManager em;

    public CursoCriteriaService(EntityManager em) {
        this.em = em;
    }

    public List<Curso> buscarPorTituloYEstado(String titulo, String estado, String nivel) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Curso> cq = cb.createQuery(Curso.class);
        Root<Curso> curso = cq.from(Curso.class);

        // 1. Creamos una lista vacía para guardar solo las condiciones que sí necesitamos
        List<Predicate> predicados = new ArrayList<>();

        // 2. Lógica para BUSCADOR INTELIGENTE (Título)
        // Si el usuario escribió algo, buscamos coincidencias parciales e ignorando mayúsculas
        if (titulo != null && !titulo.isEmpty()) {
            predicados.add(cb.like(cb.lower(curso.get("titulo")), "%" + titulo.toLowerCase() + "%"));
        }

        // 3. Lógica para ESTADO (Exacto)
        // Solo filtramos si se seleccionó un estado
        if (estado != null && !estado.isEmpty()) {
            predicados.add(cb.equal(curso.get("estado"), estado));
        }

        // 4. Lógica para NIVEL (Exacto)
        // Solo filtramos si se seleccionó un nivel
        if (nivel != null && !nivel.isEmpty()) {
            predicados.add(cb.equal(curso.get("nivel"), nivel));
        }

        // 5. Aplicamos todas las condiciones acumuladas con un AND
        cq.where(cb.and(predicados.toArray(new Predicate[0])));

        return em.createQuery(cq).getResultList();
    }
}