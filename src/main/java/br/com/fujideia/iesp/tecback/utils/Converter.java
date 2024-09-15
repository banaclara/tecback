package br.com.fujideia.iesp.tecback.utils;

import br.com.fujideia.iesp.tecback.model.*;
import br.com.fujideia.iesp.tecback.model.dto.*;

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
                        .collect(Collectors.toList()),
                film.getProducers().stream()
                        .map(Converter::convertToDTO)
                        .collect(Collectors.toList()),
                film.getSoundtrack() != null ? convertToDTO(film.getSoundtrack()) : null
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

    public static ProducerDTO convertToDTO(Producer producer) {
        return new ProducerDTO(
                producer.getId(),
                producer.getName(),
                producer.getAge(),
                producer.getNationality(),
                producer.getProducedFilms() != null ? producer.getProducedFilms().stream()
                        .map(Converter::convertToDTO)
                        .collect(Collectors.toList()) : null
        );
    }

    public static SoundtrackDTO convertToDTO(Soundtrack soundtrack) {
        return new SoundtrackDTO(
                soundtrack.getId(),
                soundtrack.getComposer(),
                soundtrack.getTracks() != null ? soundtrack.getTracks().stream()
                        .map(Converter::convertToDTO)
                        .collect(Collectors.toList()) : null,
                soundtrack.getFilm() != null ? convertToDTO(soundtrack.getFilm()) : null,
                soundtrack.totalDuration()
        );
    }

    public static TrackDTO convertToDTO(Track track) {
        return new TrackDTO(
                track.getId(),
                track.getTitle(),
                track.getDuration(),
                track.getSoundtrack() != null ? convertToDTO(track.getSoundtrack()) : null
        );
    }

    public static Film convertToEntity(FilmDTO filmDTO) {
        Film film = new Film();
        film.setTitle(filmDTO.getTitle());
        film.setReleaseYear(filmDTO.getReleaseYear());
        film.setDirector(convertToEntity(filmDTO.getDirector()));
        film.setActors(filmDTO.getActors().stream().map(Converter::convertToEntity).collect(Collectors.toList()));
        film.setGenres(filmDTO.getGenres().stream().map(Converter::convertToEntity).collect(Collectors.toList()));
        film.setProducers(filmDTO.getProducers().stream().map(Converter::convertToEntity).collect(Collectors.toList()));
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

    public static Producer convertToEntity(ProducerDTO producerDTO) {
        Producer producer = new Producer();
        producer.setId(producerDTO.getId());
        producer.setName(producerDTO.getName());
        producer.setAge(producerDTO.getAge());
        producer.setNationality(producerDTO.getNationality());
        return producer;
    }

    public static Soundtrack convertToEntity(SoundtrackDTO soundtrackDTO) {
        Soundtrack soundtrack = new Soundtrack();
        soundtrack.setId(soundtrackDTO.getId());
        soundtrack.setComposer(soundtrackDTO.getComposer());
        soundtrack.setTracks(soundtrackDTO.getTracks().stream().map(Converter::convertToEntity).collect(Collectors.toList()));
        soundtrack.setFilm(convertToEntity(soundtrackDTO.getFilm()));
        return soundtrack;
    }

    public static Track convertToEntity(TrackDTO trackDTO) {
        Track track = new Track();
        track.setId(trackDTO.getId());
        track.setTitle(trackDTO.getTitle());
        track.setDuration(trackDTO.getDuration());
        track.setSoundtrack(convertToEntity(trackDTO.getSoundtrack()));
        return track;
    }
}
