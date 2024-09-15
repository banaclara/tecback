package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.dto.SoundtrackDTO;
import br.com.fujideia.iesp.tecback.model.dto.TrackDTO;
import br.com.fujideia.iesp.tecback.service.SoundtrackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/soundtracks")
@Slf4j
public class SoundtrackController {

    private final SoundtrackService soundtrackService;

    @PostMapping
    public ResponseEntity<SoundtrackDTO> createSoundtrack(@RequestBody SoundtrackDTO soundtrackDTO) {
        log.info("Calling createSoundtrack on SoundtrackController with data: {}", soundtrackDTO);
        SoundtrackDTO createdSoundtrack = soundtrackService.createSoundtrack(soundtrackDTO);
        return ResponseEntity.ok(createdSoundtrack);
    }

    @PostMapping("/{id}/tracks")
    public ResponseEntity<Void> addTrackToSoundtrack(@PathVariable Long soundtrackId, @RequestBody TrackDTO trackDTO) {
        log.info("Calling addTrackToSoundtrack on SoundtrackController with soundtrack_id: {} and data: {}", soundtrackId, trackDTO);
        soundtrackService.addTrackToSoundtrack(soundtrackId, trackDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/tracks/{trackId}")
    public ResponseEntity<Void> removeTrackFromSoundtrack(@PathVariable Long soundtrackId, @PathVariable Long trackId) {
        log.info("Calling removeTrackFromSoundtrack on SoundtrackController with soundtrack_id: {} and track_id: {}", soundtrackId, trackId);
        soundtrackService.removeTrackFromSoundtrack(soundtrackId, trackId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<SoundtrackDTO>> findSoundtrackByComposer(@RequestParam String composer) {
        List<SoundtrackDTO> soundtracks = soundtrackService.findSoundtrackByComposer(composer);
        return ResponseEntity.ok(soundtracks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SoundtrackDTO> updateSoundtrack(@PathVariable Long id, @RequestBody SoundtrackDTO soundtrackDTO) {
        Optional<SoundtrackDTO> updatedSoundtrack = soundtrackService.updateSoundtrack(id, soundtrackDTO);
        return updatedSoundtrack.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoundtrack(@PathVariable Long id) {
        log.info("Calling deleteFilm on FilmController with id: {}", id);
        boolean deleted = soundtrackService.deleteSoundtrack(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
