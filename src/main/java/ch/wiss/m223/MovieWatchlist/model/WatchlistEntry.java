package ch.wiss.m223.MovieWatchlist.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WatchlistEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long movieId;

    private String title;

    private String posterPath;

    public WatchlistEntry(User user, Long movieId, String title, String posterPath) {
        this.posterPath = posterPath;
        this.title = title;
        this.user = user;
        this.movieId = movieId;
    }
}
