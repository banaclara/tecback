package br.com.fujideia.iesp.tecback.utils;

import br.com.fujideia.iesp.tecback.model.Actor;
import br.com.fujideia.iesp.tecback.model.Director;
import br.com.fujideia.iesp.tecback.model.Film;
import br.com.fujideia.iesp.tecback.model.Genre;
import br.com.fujideia.iesp.tecback.model.dto.ActorDTO;
import br.com.fujideia.iesp.tecback.model.dto.DirectorDTO;
import br.com.fujideia.iesp.tecback.model.dto.FilmDTO;
import br.com.fujideia.iesp.tecback.model.dto.GenreDTO;

import java.util.stream.Collectors;

public class Converter {

    public static FilmDTO convertToDTO(Film film) {
        return new FilmDTO(
                film.getId(),
                film.getTitle(),
                film.getReleaseYear(),
                film.getDirector() != null ? convertToDTO(film.getDirector()) : null,
                film.getActors().stream()
                        .map(Converter::convertToDTO)
                        .collect(Collectors.toList()),
                film.getGenres().stream()
                        .map(Converter::convertToDTO)
                        .collect(Collectors.toList())
        );
    }

    public static DirectorDTO convertToDTO(Director director) {
        return new DirectorDTO(
                director.getId(),
                director.getName(),
                director.getFilmsDirected() != null ? director.getFilmsDirected().stream()
                        .map(Converter::convertToDTO)
                        .collect(Collectors.toList()) : null
        );
    }

    public static GenreDTO convertToDTO(Genre genre) {
        return new GenreDTO(
                genre.getId(),
                genre.getName(),
                genre.getFilms() != null ? genre.getFilms().stream()
                        .map(Converter::convertToDTO)
                        .collect(Collectors.toList()) : null
        );
    }

    public static ActorDTO convertToDTO(Actor actor) {
        return new ActorDTO(
                actor.getId(),
                actor.getName(),
                actor.getFilms() != null ? actor.getFilms().stream()
                        .map(Converter::convertToDTO)
                        .collect(Collectors.toList()) : null
        );
    }

    public static Film convertToEntity(FilmDTO filmDTO) {
        Film film = new Film();
        film.setTitle(filmDTO.getTitle());
        film.setReleaseYear(filmDTO.getReleaseYear());
        film.setDirector(convertToEntity(filmDTO.getDirector()));
        film.setActors(filmDTO.getActors().stream().map(Converter::convertToEntity).collect(Collectors.toList()));
        film.setGenres(filmDTO.getGenres().stream().map(Converter::convertToEntity).collect(Collectors.toList()));
        return film;
    }

    public static Director convertToEntity(DirectorDTO directorDTO) {
        if (directorDTO == null) {
            return null;
        }
        Director director = new Director();
        director.setId(directorDTO.getId());
        director.setName(directorDTO.getName());
        return director;
    }

    public static Actor convertToEntity(ActorDTO actorDTO) {
        Actor actor = new Actor();
        actor.setId(actorDTO.getId());
        actor.setName(actorDTO.getName());
        return actor;
    }

    public static Genre convertToEntity(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setId(genreDTO.getId());
        genre.setName(genreDTO.getName());
        return genre;
    }
}
