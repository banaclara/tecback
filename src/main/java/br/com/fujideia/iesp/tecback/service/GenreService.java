package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Genre;
import br.com.fujideia.iesp.tecback.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public List<Genre> listGenres() {
        return genreRepository.findAll();
    }

    public Genre searchById(Long id) {
        var genre = genreRepository.findById(id);
        if (genre.isEmpty()) {
            throw new RuntimeException("Genre not found");
        }
        return genre.get();
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre updateGenre(Long id, Genre genre) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        if (genreOptional.isEmpty()) {
            throw new RuntimeException("Genre not found");
        }
        genre.setId(id);
        return genreRepository.save(genre);
    }

    public void deleteGenre(Long id) {
        if (genreRepository.existsById(id)) {
            genreRepository.deleteById(id);
        } else {
            throw new RuntimeException("Genre not found");
        }
    }
}
