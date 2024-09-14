package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Actor;
import br.com.fujideia.iesp.tecback.model.dto.ActorDTO;
import br.com.fujideia.iesp.tecback.repository.ActorRepository;
import br.com.fujideia.iesp.tecback.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ActorService {
    private final ActorRepository actorRepository;

    public List<ActorDTO> listActors() {
        return actorRepository.findAll().stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ActorDTO> searchById(Long id) {
        return actorRepository.findById(id)
                .map(Converter::convertToDTO);
    }

    public ActorDTO createActor(ActorDTO actorDTO) {
        Actor actor = Converter.convertToEntity(actorDTO);
        return Converter.convertToDTO(actorRepository.save(actor));
    }

    public Optional<ActorDTO> updateActor(Long id, ActorDTO actorDTO) {
        return actorRepository.findById(id).map(actor -> {
            actor.setName(actorDTO.getName());
            actor.setFilms(actorDTO.getFilms().stream().map(Converter::convertToEntity).collect(Collectors.toList()));
            return Converter.convertToDTO(actorRepository.save(actor));
        });
    }

    public boolean deleteActor(Long id) {
        if (actorRepository.existsById(id)) {
            actorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
