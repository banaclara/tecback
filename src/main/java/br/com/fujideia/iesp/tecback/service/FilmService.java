package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Film;
import br.com.fujideia.iesp.tecback.model.dto.FilmDTO;
import br.com.fujideia.iesp.tecback.repository.FilmRepository;
import br.com.fujideia.iesp.tecback.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public List<FilmDTO> listFilms() {
        return filmRepository.findAll().stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<FilmDTO> searchById(Long id) {
        return filmRepository.findById(id)
                .map(Converter::convertToDTO);
    }

    public FilmDTO createFilm(FilmDTO filmDTO) {
        Film film = Converter.convertToEntity(filmDTO);
        return Converter.convertToDTO(filmRepository.save(film));
    }

    public Optional<FilmDTO> updateFilm(Long id, FilmDTO filmDTO) {
        return filmRepository.findById(id).map(film -> {
            if (filmDTO.getTitle() != null) { film.setTitle(filmDTO.getTitle()); }
            if (filmDTO.getReleaseYear() != null) { film.setReleaseYear(filmDTO.getReleaseYear()); }
            if (filmDTO.getDirector() != null) { film.setDirector(Converter.convertToEntity(filmDTO.getDirector())); }
            if (filmDTO.getActors() != null) { film.setActors(filmDTO.getActors().stream().map(Converter::convertToEntity).collect(Collectors.toList())); }
            if (filmDTO.getGenres() != null) { film.setGenres(filmDTO.getGenres().stream().map(Converter::convertToEntity).collect(Collectors.toList())); }
            if (filmDTO.getProducers() != null) { film.setProducers(filmDTO.getProducers().stream().map(Converter::convertToEntity).collect(Collectors.toList())); }
            if (filmDTO.getSoundtrack() != null) { film.setSoundtrack(Converter.convertToEntity(filmDTO.getSoundtrack())); }
            return Converter.convertToDTO(filmRepository.save(film));
        });
    }

    public boolean deleteFilm(Long id) {
        if (filmRepository.existsById(id)) {
            filmRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
