package ch.wiss.m223.MovieWatchlist.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class RatingDTO {
    public Long movieId;
    public int score;
    public String comment;

    public String title;
    public String description;
    public String director;
    public int duration;
    public int year;
}
