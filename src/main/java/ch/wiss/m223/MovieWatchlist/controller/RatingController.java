package ch.wiss.m223.MovieWatchlist.controller;

import ch.wiss.m223.MovieWatchlist.dto.RatingDTO;
import ch.wiss.m223.MovieWatchlist.model.Rating;
import ch.wiss.m223.MovieWatchlist.model.UserDetailsImpl;
import ch.wiss.m223.MovieWatchlist.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@CrossOrigin(origins = "http://localhost:5173")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    // Bewertung über einzelne Parameter speichern (Query-Variante)
    @PostMapping("/manual")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Rating addRatingViaParams(
            @RequestParam Long movieId,
            @RequestParam int score,
            @RequestParam(required = false) String comment,
            Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        return ratingService.saveRating(userId, movieId, score, comment);
    }


    // Bewertung über RequestBody (DTO oder Rating direkt)
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Rating> createRating(
            @RequestBody RatingDTO ratingDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Rating rating = ratingService.createRating(userDetails.getId(), ratingDto);
        return ResponseEntity.ok(rating);
    }

    // Bewertungen des eingeloggten Users abrufen
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Rating> getMyRatings(Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        return ratingService.getRatingsByUser(userId);
    }

    // Bewertung löschen
    @DeleteMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteRating(@RequestParam Long movieId, Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        ratingService.deleteRating(userId, movieId);
    }
}
