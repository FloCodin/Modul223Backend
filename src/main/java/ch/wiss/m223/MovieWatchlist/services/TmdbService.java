package ch.wiss.m223.MovieWatchlist.services;

import ch.wiss.m223.MovieWatchlist.model.Movie;
import ch.wiss.m223.MovieWatchlist.repositories.MovieRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

@Service
public class TmdbService {

    private final MovieRepository movieRepository;
    private final RestTemplate restTemplate;

    @Value("${tmdb.api.token}")
    private String apiToken;

    @Value("${tmdb.base.url}")
    private String baseUrl;

    public TmdbService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        this.restTemplate = new RestTemplate();
    }

    public JsonNode searchMovie(String title) {
        String query = UriUtils.encode(title, StandardCharsets.UTF_8);
        String url = baseUrl + "/search/movie?query=" + query;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        try {
            return new ObjectMapper().readTree(response.getBody());
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Parsen der TMDB-Suchergebnisse", e);
        }
    }

    public JsonNode getPopularMovies() {
        String url = baseUrl + "/movie/popular";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        try {
            return new ObjectMapper().readTree(response.getBody());
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Parsen der TMDB-Antwort (popular)", e);
        }
    }

    public Movie fetchMovieFromTmdb(Long movieId) {
        Optional<Movie> existing = movieRepository.findById(movieId);
        if (existing.isPresent()) {
            return existing.get();
        }

        String url = baseUrl + "/movie/" + movieId + "?language=de-DE";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> data = response.getBody();

            Movie movie = new Movie();
            movie.setId(movieId);
            movie.setTitle((String) data.get("title"));
            movie.setDescription((String) data.get("overview"));
            movie.setYear(Integer.parseInt(((String) data.get("release_date")).substring(0, 4)));
            movie.setDuration((Integer) data.get("runtime"));
            movie.setDirector("Unbekannt");

            return movieRepository.save(movie);
        }

        throw new RuntimeException("Movie not found");
    }

    @PostConstruct
    public void logConfig() {
        System.out.println("TMDB Config geladen:");
        System.out.println("Base URL: " + baseUrl);
        System.out.println("Bearer Token: " + (apiToken != null ? "✅ gesetzt" : "❌ fehlt"));
    }
}
