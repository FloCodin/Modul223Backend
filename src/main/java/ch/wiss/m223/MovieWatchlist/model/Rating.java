package ch.wiss.m223.MovieWatchlist.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rating {

    @EmbeddedId
    private RatingId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private int score;

    @Column(length = 1000)
    private String comment;

    public Rating(User user, Movie movie, int score, String comment) {
        this.user = user;
        this.movie = movie;
        this.score = score;
        this.comment = comment;
        this.id = new RatingId((long) Math.toIntExact(user.getId()), movie.getId());

    }
}
