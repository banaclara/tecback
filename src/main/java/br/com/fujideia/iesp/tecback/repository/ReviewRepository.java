package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.film.id = :filmId")
    List<Review> findByFilm(@Param("filmId") Long filmId);
}
