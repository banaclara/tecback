package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Actor;
import br.com.fujideia.iesp.tecback.model.Film;
import br.com.fujideia.iesp.tecback.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ActorRepository actorRepository;

    @GetMapping
    public List<Actor> listAll() {
        return actorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> searchById(@PathVariable Long id) {
        Optional<Actor> actor = actorRepository.findById(id);
        return actor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Actor createActor(@RequestBody Actor actor) {
        return actorRepository.save(actor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable Long id, @RequestBody Actor actorDetails) {
        Optional<Actor> actorOptional = actorRepository.findById(id);
        if (actorOptional.isPresent()) {
            Actor actor = actorOptional.get();
            actor.setName(actorDetails.getName());
            Actor actorUpdated = actorRepository.save(actor);
            return ResponseEntity.ok(actorUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeActor(@PathVariable Long id) {
        if (actorRepository.existsById(id)) {
            actorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}