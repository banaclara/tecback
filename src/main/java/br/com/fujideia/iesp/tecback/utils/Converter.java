package br.com.fujideia.iesp.tecback.utils;

import br.com.fujideia.iesp.tecback.model.*;
import br.com.fujideia.iesp.tecback.model.dto.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Converter {

    public static FilmDTO convertToDTO(Film film) {
        return new FilmDTO(
                film.getId(),
                film.getTitle(),
                film.getReleaseYear(),
                film.getDirector() != null ? convertToDTO(film.getDirector()) : null,
                film.getActors() != null ?
                        film.getActors().stream()
                                .map(Converter::convertToDTO)
                                .collect(Collectors.toList()) : Collections.emptyList(),
                film.getGenres() != null ?
                        film.getGenres().stream()
                                .map(Converter::convertToDTO)
                                .collect(Collectors.toList()) : Collections.emptyList(),
                film.getProducers() != null ?
                        film.getProducers().stream()
                                .map(Converter::convertToDTO)
                                .collect(Collectors.toList()) : Collections.emptyList(),
                film.getSoundtrack() != null ? convertToDTO(film.getSoundtrack()) : null
        );
    }

    public static DirectorDTO convertToDTO(Director director) {
        return new DirectorDTO(
                director.getId(),
                director.getName()
        );
    }

    public static GenreDTO convertToDTO(Genre genre) {
        return new GenreDTO(
                genre.getId(),
                genre.getName()
        );
    }

    public static ActorDTO convertToDTO(Actor actor) {
        return new ActorDTO(
                actor.getId(),
                actor.getName()
        );
    }

    public static ProducerDTO convertToDTO(Producer producer) {
        return new ProducerDTO(
                producer.getId(),
                producer.getName(),
                producer.getAge(),
                producer.getNationality()
        );
    }

    public static SoundtrackDTO convertToDTO(Soundtrack soundtrack) {
        return new SoundtrackDTO(
                soundtrack.getId(),
                soundtrack.getComposer(),
                soundtrack.getFilm() != null ? convertToDTO(soundtrack.getFilm()) : null,
                soundtrack.getTracks() != null ?
                        soundtrack.getTracks().stream()
                                .map(Converter::convertToDTO)
                                .collect(Collectors.toList()) : Collections.emptyList(),
                soundtrack.totalDuration()
        );
    }

    public static TrackDTO convertToDTO(Track track) {
        return new TrackDTO(
                track.getId(),
                track.getTitle(),
                track.getDuration()
        );
    }

    public static ReviewDTO convertToDTO(Review review) {
        return new ReviewDTO(
                review.getId(),
                review.getAuthor(),
                review.getRating(),
                review.getComment(),
                convertToDTO(review.getFilm())
        );
    }

    public static Film convertToEntity(FilmDTO filmDTO) {
        Film film = new Film();
        film.setTitle(filmDTO.getTitle());
        film.setReleaseYear(filmDTO.getReleaseYear());
        film.setDirector(filmDTO.getDirector() != null ? convertToEntity(filmDTO.getDirector()) : null);
        film.setActors(filmDTO.getActors() != null ?
                filmDTO.getActors().stream().map(Converter::convertToEntity).collect(Collectors.toList()) :
                new ArrayList<>());
        film.setGenres(filmDTO.getGenres() != null ?
                filmDTO.getGenres().stream().map(Converter::convertToEntity).collect(Collectors.toList()) :
                new ArrayList<>());
        film.setProducers(filmDTO.getProducers() != null ?
                filmDTO.getProducers().stream().map(Converter::convertToEntity).collect(Collectors.toList()) :
                new ArrayList<>());
        film.setSoundtrack(filmDTO.getSoundtrack() != null ? convertToEntity(filmDTO.getSoundtrack()) : null);
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
        soundtrack.setFilm(soundtrackDTO.getFilm() != null ? convertToEntity(soundtrackDTO.getFilm()) : null);
        soundtrack.setTracks(soundtrackDTO.getTracks() != null ?
                soundtrackDTO.getTracks().stream().map(Converter::convertToEntity).collect(Collectors.toList()) :
                new ArrayList<>());
        return soundtrack;
    }

    public static Track convertToEntity(TrackDTO trackDTO) {
        Track track = new Track();
        track.setId(trackDTO.getId());
        track.setTitle(trackDTO.getTitle());
        track.setDuration(trackDTO.getDuration());
        return track;
    }

    public static Review convertToEntity(ReviewDTO reviewDTO, Film film) {
        Review review = new Review();
        review.setAuthor(reviewDTO.getAuthor());
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setFilm(film != null ? film : null);
        return review;
    }
}
