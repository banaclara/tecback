package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Genre;
import br.com.fujideia.iesp.tecback.model.dto.DirectorDTO;
import br.com.fujideia.iesp.tecback.model.dto.GenreDTO;
import br.com.fujideia.iesp.tecback.repository.GenreRepository;
import br.com.fujideia.iesp.tecback.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/genre")
@Slf4j
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> listAll() {
        log.info("listAll on GenreController");
        List<GenreDTO> genres = genreService.listGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> searchById(@PathVariable Long id) {
        log.info("Calling searchById on GenreController with id: {}", id);
        Optional<GenreDTO> genre = genreService.searchById(id);
        return genre.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(@RequestBody GenreDTO genreDTO) {
        log.info("Calling createGenre on GenreController with data: {}", genreDTO);
        GenreDTO createdGenre = genreService.createGenre(genreDTO);
        return ResponseEntity.ok(createdGenre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable Long id, @RequestBody GenreDTO genreDetails) {
        log.info("Calling updateGenre on GenreController with id: {} and data: {}", id, genreDetails);
        Optional<GenreDTO> updatedGenre = genreService.updateGenre(id, genreDetails);
        return updatedGenre.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeGenre(@PathVariable Long id) {
        log.info("Calling deleteGenre on GenreController with id: {}", id);
        boolean deleted = genreService.deleteGenre(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
