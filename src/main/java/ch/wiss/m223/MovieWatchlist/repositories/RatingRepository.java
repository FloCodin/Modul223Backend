package ch.wiss.m223.MovieWatchlist.repositories;

import ch.wiss.m223.MovieWatchlist.model.Rating;
import ch.wiss.m223.MovieWatchlist.model.RatingId;
import ch.wiss.m223.MovieWatchlist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, RatingId> {

    Optional<Rating> findByUserAndMovieId(User user, Long movieId);

    List<Rating> findByUser(User user);
}
