package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.dto.FilmDTO;
import br.com.fujideia.iesp.tecback.model.dto.ProducerDTO;
import br.com.fujideia.iesp.tecback.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/producers")
@Slf4j
public class ProducerController {
    private final ProducerService producerService;

    @GetMapping
    public ResponseEntity<List<ProducerDTO>> listAll() {
        log.info("Calling listAll on ProducerController");
        List<ProducerDTO> producers = producerService.listProducers();
        return ResponseEntity.ok(producers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProducerDTO> searchById(@PathVariable Long id) {
        log.info("Calling searchById on producerController with id: {}", id);
        Optional<ProducerDTO> producer = producerService.searchById(id);
        return producer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProducerDTO>> searchByName(@RequestParam String name) {
        log.info("Calling searchByName on ProducerController to find producers with name: {}", name);
        List<ProducerDTO> producers = producerService.searchByName(name);
        return ResponseEntity.ok(producers);
    }

    @GetMapping("/{producerId}/films")
    public ResponseEntity<List<FilmDTO>> listFilmsByProducer(@PathVariable Long producerId) {
        log.info("Calling listFilmsByProducer on ProducerController with id: {}", producerId);
        List<FilmDTO> films = producerService.listFilmsProduced(producerId);
        return ResponseEntity.ok(films);
    }
    
    @PostMapping("/{producerId}/films/{filmId}")
    public ResponseEntity<ProducerDTO> addFilmToProducer(@PathVariable Long producerId, @PathVariable Long filmId) {
        log.info("Calling addFilmToProducer on ProducerController with producer_id: {} and film_id: {}", producerId, filmId);
        ProducerDTO updatedProducer = producerService.addFilmToProducer(producerId, filmId);
        return ResponseEntity.ok(updatedProducer);
    }

    @PostMapping
    public ResponseEntity<ProducerDTO> createProducer(@RequestBody ProducerDTO producerDTO) {
        log.info("Calling createProducer on ProducerController with data: {}", producerDTO);
        ProducerDTO createdProducer = producerService.createProducer(producerDTO);
        return ResponseEntity.ok(createdProducer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProducerDTO> updateProducer(@PathVariable Long id, @RequestBody ProducerDTO producerDetails) {
        log.info("Calling updateProducer on ProducerController with id: {} and data: {}", id, producerDetails);
        Optional<ProducerDTO> updatedproducer = producerService.updateProducer(id, producerDetails);
        return updatedproducer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProducer(@PathVariable Long id) {
        log.info("Calling deleteProducer on ProducerController with id: {}", id);
        boolean deleted = producerService.deleteProducer(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
