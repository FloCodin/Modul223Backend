// === BACKEND TEST 2: WatchlistServiceTest.java ===
package ch.wiss.m223.MovieWatchlist.services.WatchlistService;

import ch.wiss.m223.MovieWatchlist.model.User;
import ch.wiss.m223.MovieWatchlist.repositories.UserRepository;
import ch.wiss.m223.MovieWatchlist.services.WatchlistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WatchlistServiceTest {

    @Autowired
    private WatchlistService watchlistService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddAndRemoveWatchlistEntry() {
        User user = userRepository.findByUsername("admin123").orElseThrow();
        Long movieId = 123L;

        watchlistService.addToWatchlist(movieId);
        assertTrue(
                watchlistService.getMyWatchlist().stream()
                        .anyMatch(dto -> dto.getMovieId().equals(movieId))
        );

        watchlistService.removeFromWatchlist(movieId);
        assertFalse(
                watchlistService.getMyWatchlist().stream()
                        .anyMatch(dto -> dto.getMovieId().equals(movieId))
        );
    }
}
