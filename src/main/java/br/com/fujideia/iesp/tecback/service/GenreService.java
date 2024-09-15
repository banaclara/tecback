package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Genre;
import br.com.fujideia.iesp.tecback.model.dto.GenreDTO;
import br.com.fujideia.iesp.tecback.repository.GenreRepository;
import br.com.fujideia.iesp.tecback.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public List<GenreDTO> listGenres() {
        return genreRepository.findAll().stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<GenreDTO> searchById(Long id) {
        return genreRepository.findById(id)
                .map(Converter::convertToDTO);
    }

    public GenreDTO createGenre(GenreDTO genreDTO) {
        Genre genre = Converter.convertToEntity(genreDTO);
        return Converter.convertToDTO(genreRepository.save(genre));
    }

    public Optional<GenreDTO> updateGenre(Long id, GenreDTO genreDTO) {
        return genreRepository.findById(id).map(genre -> {
            genre.setName(genreDTO.getName());
            return Converter.convertToDTO(genreRepository.save(genre));
        });
    }

    public boolean deleteGenre(Long id) {
        if (genreRepository.existsById(id)) {
            genreRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
