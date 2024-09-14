package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Actor;
import br.com.fujideia.iesp.tecback.model.Film;
import br.com.fujideia.iesp.tecback.model.dto.ActorDTO;
import br.com.fujideia.iesp.tecback.repository.ActorRepository;
import br.com.fujideia.iesp.tecback.service.ActorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/actors")
@Slf4j
public class ActorController {

    private final ActorService actorService;

    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> searchById(@PathVariable Long id) {
        log.info("Calling searchById on ActorController with id: {}", id);
        Optional<ActorDTO> actor = actorService.searchById(id);
        return actor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ActorDTO> createActor(@RequestBody ActorDTO actorDTO) {
        log.info("Calling createActor on ActorController with data: {}", actorDTO);
        ActorDTO createdActor = actorService.createActor(actorDTO);
        return ResponseEntity.ok(createdActor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorDTO> updateActor(@PathVariable Long id, @RequestBody ActorDTO actorDetails) {
        log.info("Calling updateActor on ActorController with id: {} and data: {}", id, actorDetails);
        Optional<ActorDTO> updatedActor = actorService.updateActor(id, actorDetails);
        return updatedActor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeActor(@PathVariable Long id) {
        log.info("Calling deleteActor on ActorController with id: {}", id);
        boolean deleted = actorService.deleteActor(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}