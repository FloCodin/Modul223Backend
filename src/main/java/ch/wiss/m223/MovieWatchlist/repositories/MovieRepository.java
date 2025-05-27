package ch.wiss.m223.MovieWatchlist.repositories;


import ch.wiss.m223.MovieWatchlist.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {}
