package com.ucb.plataforma.res.reviews.repository;

import com.ucb.plataforma.res.reviews.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 🔹 Reseñas por curso (para US-019)
    List<Review> findByCourseId(Long courseId);

    // 🔹 Filtrar por rating mínimo
    List<Review> findByRatingGreaterThanEqual(int minRating);

    // 🔹 Buscar por palabra en el comentario
    @Query("SELECT r FROM Review r WHERE LOWER(r.comment) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Review> searchByCommentContaining(@Param("keyword") String keyword);

    // 🔹 Soporte paginado (en realidad JpaRepository ya lo trae, pero no molesta)
    Page<Review> findAll(Pageable pageable);
}
