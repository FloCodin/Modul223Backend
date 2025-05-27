package ch.wiss.m223.MovieWatchlist.services;

import ch.wiss.m223.MovieWatchlist.model.Movie;
import ch.wiss.m223.MovieWatchlist.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    public Movie getById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }
}
