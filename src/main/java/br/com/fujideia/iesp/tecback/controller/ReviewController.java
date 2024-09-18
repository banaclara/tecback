package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.dto.ReviewDTO;
import br.com.fujideia.iesp.tecback.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{filmId}")
    public ResponseEntity<ReviewDTO> reviewFilm(@RequestBody ReviewDTO reviewDTO, @PathVariable Long filmId) {
        log.info("Calling reviewFilm on ReviewController with data: {}", reviewDTO);
        ReviewDTO createdReview = reviewService.reviewFilm(reviewDTO, filmId);
        return ResponseEntity.ok(createdReview);
    }

    @GetMapping("/{filmId}")
    public ResponseEntity<List<ReviewDTO>> listFilmReviews(@PathVariable Long filmId) {
        log.info("Calling listFilmReviews on ReviewController with film id: {}", filmId);
        List<ReviewDTO> reviews = reviewService.listFilmReviews(filmId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{filmId}/rating")
    public ResponseEntity<String> calculateFilmAverageRating(@PathVariable Long filmId) {
        log.info("Calling calculateFilmAverageRating on ReviewController with film id: {}", filmId);
        double averageRating = reviewService.calculateRating(filmId);
        if (Double.isNaN(averageRating)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(String.format("Average rating: %.2f", averageRating));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        log.info("Calling deleteReview on ReviewController with id: {}", id);
        boolean deleted = reviewService.deleteReview(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
