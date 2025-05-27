package ch.wiss.m223.MovieWatchlist.controller;

import ch.wiss.m223.MovieWatchlist.services.TmdbService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tmdb")
@CrossOrigin(origins = "http://localhost:5173")
public class TmdbController {


    @Autowired
    private TmdbService tmdbService;

    @GetMapping("/search")
    @PreAuthorize("permitAll()") // Jeder darf suchen
    public JsonNode searchMovie(@RequestParam String title) {
        return tmdbService.searchMovie(title);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/popular")
    public JsonNode getPopularMovies() {
        return tmdbService.getPopularMovies();
    }
}
