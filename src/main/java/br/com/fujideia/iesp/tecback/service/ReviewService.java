package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Film;
import br.com.fujideia.iesp.tecback.model.Review;
import br.com.fujideia.iesp.tecback.model.dto.ReviewDTO;
import br.com.fujideia.iesp.tecback.repository.FilmRepository;
import br.com.fujideia.iesp.tecback.repository.ReviewRepository;
import br.com.fujideia.iesp.tecback.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final FilmRepository filmRepository;

    public ReviewDTO reviewFilm(ReviewDTO reviewDTO, Long filmId) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Film with id " + filmId + " not found"));
        Review review = Converter.convertToEntity(reviewDTO, film);
        return Converter.convertToDTO(reviewRepository.save(review));
    }

    public List<ReviewDTO> listFilmReviews(Long filmId) {
        return reviewRepository.findByFilm(filmId).stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public double calculateRating(Long filmId) {
        List<ReviewDTO> reviews = reviewRepository.findByFilm(filmId).stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
        return reviews.stream()
                .mapToInt(ReviewDTO::getRating)
                .average()
                .orElse(Double.NaN);
    }

    public boolean deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
