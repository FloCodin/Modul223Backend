package ch.wiss.m223.MovieWatchlist.services;

import ch.wiss.m223.MovieWatchlist.dto.RatingDTO;
import ch.wiss.m223.MovieWatchlist.model.*;
import ch.wiss.m223.MovieWatchlist.repositories.MovieRepository;
import ch.wiss.m223.MovieWatchlist.repositories.RatingRepository;
import ch.wiss.m223.MovieWatchlist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private TmdbService tmdbService;

    public List<Rating> getRatingsByUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(ratingRepository::findByUser).orElse(List.of());
    }

    public Rating saveRating(Long userId, Long movieId, int score, String comment) {
        User user = userRepository.findById(userId).orElseThrow();
        Movie movie = movieRepository.findById(movieId).orElseThrow();

        Rating rating = new Rating(user, movie, score, comment);
        return ratingRepository.save(rating);
    }

    public Rating createRating(Long userId, RatingDTO ratingDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Movie aktualisieren oder von TMDB laden
        Movie movie = movieRepository.findById(ratingDto.movieId)
                .orElseGet(() -> tmdbService.fetchMovieFromTmdb(ratingDto.movieId));

        // Optional: lokale Daten mit DTO aktualisieren
        movie.setTitle(ratingDto.title);
        movie.setDescription(ratingDto.description);
        movie.setYear(ratingDto.year);
        movie.setDuration(ratingDto.duration);
        movie.setDirector(ratingDto.director);
        movieRepository.save(movie);

        // Bewertung speichern oder aktualisieren
        Rating rating = new Rating(user, movie, ratingDto.score, ratingDto.comment);
        return ratingRepository.save(rating);
    }

    public void deleteRating(Long userId, Long movieId) {
        RatingId id = new RatingId(userId, movieId);
        ratingRepository.deleteById(id);
    }
}
