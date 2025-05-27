package ch.wiss.m223.MovieWatchlist.controller;

import ch.wiss.m223.MovieWatchlist.dto.WatchlistItemDto;
import ch.wiss.m223.MovieWatchlist.model.UserDetailsImpl;
import ch.wiss.m223.MovieWatchlist.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
@CrossOrigin(origins = "http://localhost:5173")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @PostMapping("/{movieId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void addToWatchlist(@PathVariable Long movieId) {
        watchlistService.addToWatchlist(movieId);
    }

    @DeleteMapping("/{movieId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void removeFromWatchlist(@PathVariable Long movieId) {
        watchlistService.removeFromWatchlist(movieId);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<WatchlistItemDto> getWatchlist() {
        return watchlistService.getMyWatchlist();
    }
}
