package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Genre;
import br.com.fujideia.iesp.tecback.repository.GenreRepository;
import br.com.fujideia.iesp.tecback.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public List<Genre> listAll() {
        return genreService.listGenres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> searchById(@PathVariable Long id) {
        return ResponseEntity.ok(genreService.searchById(id));
    }

    @PostMapping
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.createGenre(genre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre genreDetails) {
        return ResponseEntity.ok(genreService.updateGenre(id, genreDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
