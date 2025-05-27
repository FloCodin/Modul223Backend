package ch.wiss.m223.MovieWatchlist.repositories;

import ch.wiss.m223.MovieWatchlist.model.User;
import ch.wiss.m223.MovieWatchlist.model.WatchlistEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchlistRepository extends JpaRepository<WatchlistEntry, Long> {
    boolean existsByUserAndMovieId(User user, Long movieId);
    void deleteByUserAndMovieId(User user, Long movieId);
    List<WatchlistEntry> findByUser(User user);
}
