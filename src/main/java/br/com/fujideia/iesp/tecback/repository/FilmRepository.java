package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
}