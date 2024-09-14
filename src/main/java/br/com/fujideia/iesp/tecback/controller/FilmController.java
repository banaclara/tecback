package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.dto.FilmDTO;
import br.com.fujideia.iesp.tecback.service.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {

    private final FilmService filmService;

    @GetMapping
    public ResponseEntity<List<FilmDTO>> listAll() {
        log.info("listAll on FilmController");
        List<FilmDTO> films = filmService.listFilms();
        return ResponseEntity.ok(films);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> searchById(@PathVariable Long id) {
        log.info("Calling searchById on FilmController with id: {}", id);
        Optional<FilmDTO> film = filmService.searchById(id);
        return film.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FilmDTO> createFilm(@RequestBody FilmDTO filmDTO) {
        log.info("Calling createFilm on FilmController with data: {}", filmDTO);
        FilmDTO createdFilm = filmService.createFilm(filmDTO);
        return ResponseEntity.ok(createdFilm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmDTO> updateFilm(@PathVariable Long id, @RequestBody FilmDTO filmDTO) {
        log.info("Calling updateFilm on FilmController with id: {} and data: {}", id, filmDTO);
        Optional<FilmDTO> updatedFilm = filmService.updateFilm(id, filmDTO);
        return updatedFilm.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        log.info("Calling deleteFilm on FilmController with id: {}", id);
        boolean deleted = filmService.deleteFilm(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}