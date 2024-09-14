package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Director;
import br.com.fujideia.iesp.tecback.model.dto.DirectorDTO;
import br.com.fujideia.iesp.tecback.repository.DirectorRepository;
import br.com.fujideia.iesp.tecback.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DirectorService {

    private final DirectorRepository directorRepository;

    public List<DirectorDTO> listDirectors() {
        return directorRepository.findAll().stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<DirectorDTO> searchById(Long id) {
        return directorRepository.findById(id)
                .map(Converter::convertToDTO);
    }

    public DirectorDTO createDirector(DirectorDTO directorDTO) {
        Director director = Converter.convertToEntity(directorDTO);
        return Converter.convertToDTO(directorRepository.save(director));
    }

    public Optional<DirectorDTO> updateDirector(Long id, DirectorDTO directorDTO) {
        return directorRepository.findById(id).map(director -> {
            director.setName(directorDTO.getName());
            director.setFilmsDirected(directorDTO.getFilmsDirected().stream().map(Converter::convertToEntity).collect(Collectors.toList()));
            return Converter.convertToDTO(directorRepository.save(director));
        });
    }

    public boolean deleteDirector(Long id) {
        if (directorRepository.existsById(id)) {
            directorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
