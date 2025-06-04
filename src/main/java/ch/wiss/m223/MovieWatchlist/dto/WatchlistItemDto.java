package ch.wiss.m223.MovieWatchlist.dto;

import ch.wiss.m223.MovieWatchlist.model.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WatchlistItemDto {
    private Long id;
    private String title;
    private String posterPath;
    private Integer score;    // optional
    private String comment;   // optional

    public WatchlistItemDto(Long id, String title, String posterPath, Rating rating) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        if (rating != null) {
            this.score = rating.getScore();
            this.comment = rating.getComment();
        }
    }
}
