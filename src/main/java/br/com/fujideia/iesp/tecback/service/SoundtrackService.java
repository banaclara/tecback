package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Soundtrack;
import br.com.fujideia.iesp.tecback.model.Track;
import br.com.fujideia.iesp.tecback.model.dto.SoundtrackDTO;
import br.com.fujideia.iesp.tecback.model.dto.TrackDTO;
import br.com.fujideia.iesp.tecback.repository.SoundtrackRepository;
import br.com.fujideia.iesp.tecback.repository.TrackRepository;
import br.com.fujideia.iesp.tecback.utils.Converter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SoundtrackService {
    private final SoundtrackRepository soundtrackRepository;
    private final TrackRepository trackRepository;

    public SoundtrackDTO createSoundtrack(SoundtrackDTO soundtrackDTO) {
        Soundtrack soundtrack = Converter.convertToEntity(soundtrackDTO);
        return Converter.convertToDTO(soundtrackRepository.save(soundtrack));
    }

    public void addTrackToSoundtrack(Long soundtrackId, TrackDTO trackDTO) {
        Soundtrack soundtrack = soundtrackRepository.findById(soundtrackId).orElseThrow(() -> new EntityNotFoundException("Soundtrack not found"));
        Track track = new Track();
        track.setTitle(trackDTO.getTitle());
        track.setDuration(trackDTO.getDuration());
        track.setSoundtrack(soundtrack);

        if (soundtrack.getTracks() == null) {
            soundtrack.setTracks(new ArrayList<>());
        }

        soundtrack.getTracks().add(track);
        trackRepository.save(track);
        soundtrackRepository.save(soundtrack);
    }

    public void removeTrackFromSoundtrack(Long soundtrackId, Long trackId) {
        Soundtrack soundtrack = soundtrackRepository.findById(soundtrackId).orElseThrow(() -> new EntityNotFoundException("Soundtrack not found"));
        Track track = trackRepository.findById(trackId).orElseThrow(() -> new EntityNotFoundException("Track not found"));

        soundtrack.getTracks().remove(track);
        trackRepository.delete(track);
        soundtrackRepository.save(soundtrack);
    }

    public List<SoundtrackDTO> findSoundtrackByComposer(String composer) {
        return soundtrackRepository.findByComposer(composer).stream().map(Converter::convertToDTO).collect(Collectors.toList());
    }

    public Optional<SoundtrackDTO> updateSoundtrack(Long id, SoundtrackDTO soundtrackDTO) {
        return soundtrackRepository.findById(id).map(soundtrack -> {
            soundtrack.setComposer(soundtrackDTO.getComposer());
            if (soundtrackDTO.getTracks() != null) { soundtrack.setTracks(soundtrackDTO.getTracks().stream().map(Converter::convertToEntity).collect(Collectors.toList())); }
            return Converter.convertToDTO(soundtrackRepository.save(soundtrack));
        });
    }

    public boolean deleteSoundtrack(Long id) {
        if (soundtrackRepository.existsById(id)) {
            soundtrackRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
