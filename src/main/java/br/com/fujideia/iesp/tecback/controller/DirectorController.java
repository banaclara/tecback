package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Director;
import br.com.fujideia.iesp.tecback.model.dto.DirectorDTO;
import br.com.fujideia.iesp.tecback.model.dto.FilmDTO;
import br.com.fujideia.iesp.tecback.repository.DirectorRepository;
import br.com.fujideia.iesp.tecback.service.DirectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/directors")
@Slf4j
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    public ResponseEntity<List<DirectorDTO>> listAll() {
        log.info("listAll on DirectorController");
        List<DirectorDTO> directors = directorService.listDirectors();
        return ResponseEntity.ok(directors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDTO> searchById(@PathVariable Long id) {
        log.info("Calling searchById on DirectorController with id: {}", id);
        Optional<DirectorDTO> director = directorService.searchById(id);
        return director.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DirectorDTO> createDirector(@RequestBody DirectorDTO directorDTO) {
        log.info("Calling createDirector on DirectorController with data: {}", directorDTO);
        DirectorDTO createdDirector = directorService.createDirector(directorDTO);
        return ResponseEntity.ok(createdDirector);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorDTO> updateDirector(@PathVariable Long id, @RequestBody DirectorDTO directorDetails) {
        log.info("Calling updateDirector on DirectorController with id: {} and data: {}", id, directorDetails);
        Optional<DirectorDTO> updatedDirector = directorService.updateDirector(id, directorDetails);
        return updatedDirector.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeDirector(@PathVariable Long id) {
        log.info("Calling deleteDirector on DirectorController with id: {}", id);
        boolean deleted = directorService.deleteDirector(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
