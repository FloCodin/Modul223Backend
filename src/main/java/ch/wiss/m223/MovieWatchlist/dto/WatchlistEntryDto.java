package ch.wiss.m223.MovieWatchlist.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WatchlistEntryDto {
    private Long movieId;
    private String comment;
    private int score;

    public WatchlistEntryDto(Long movieId, String comment, int score) {
        this.movieId = movieId;
        this.comment = comment;
        this.score = score;
    }

}
