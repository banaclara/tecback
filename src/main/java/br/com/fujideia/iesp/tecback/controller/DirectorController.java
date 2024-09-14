package br.com.fujideia.iesp.tecback.controller;


import br.com.fujideia.iesp.tecback.model.Director;
import br.com.fujideia.iesp.tecback.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/directors")
public class DirectorController {

    private final DirectorRepository directorRepository;

    @GetMapping
    public List<Director> listAll() {
        return directorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> searchById(@PathVariable Long id) {
        Optional<Director> director = directorRepository.findById(id);
        return director.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Director createDirector(@RequestBody Director director) {
        return directorRepository.save(director);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Director> updateDirector(@PathVariable Long id, @RequestBody Director directorDetails) {
        Optional<Director> directorOptional = directorRepository.findById(id);
        if (directorOptional.isPresent()) {
            Director director = directorOptional.get();
            director.setName(directorDetails.getName());
            director.setFilmsDirected(directorDetails.getFilmsDirected());
            Director directorUpdated = directorRepository.save(director);
            return ResponseEntity.ok(directorUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeDirector(@PathVariable Long id) {
        if (directorRepository.existsById(id)) {
            directorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
