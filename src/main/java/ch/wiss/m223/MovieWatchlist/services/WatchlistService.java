package ch.wiss.m223.MovieWatchlist.services;

import ch.wiss.m223.MovieWatchlist.dto.WatchlistItemDto;
import ch.wiss.m223.MovieWatchlist.model.*;
import ch.wiss.m223.MovieWatchlist.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow();
    }

    public void addToWatchlist(Long movieId) {
        User user = getCurrentUser();
        if (!watchlistRepository.existsByUserAndMovieId(user, movieId)) {
            WatchlistEntry entry = new WatchlistEntry(user, movieId);
            watchlistRepository.save(entry);
        }
    }

    public void removeFromWatchlist(Long movieId) {
        User user = getCurrentUser();
        watchlistRepository.deleteByUserAndMovieId(user, movieId);
    }

    public List<WatchlistItemDto> getMyWatchlist() {
        User user = getCurrentUser();
        List<WatchlistEntry> entries = watchlistRepository.findByUser(user);

        return entries.stream()
                .map(entry -> {
                    Optional<Rating> ratingOpt = ratingRepository.findByUserAndMovieId(user, entry.getMovieId());
                    Rating rating = ratingOpt.orElse(null);
                    return new WatchlistItemDto(entry.getMovieId(), rating);
                })
                .collect(Collectors.toList());
    }
}
