package ch.wiss.m223.MovieWatchlist.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private int year;

    private String director;  // 👈 Regisseur

    private int duration;     // 👈 Dauer in Minuten

    public Movie(String title, String description, int year, String director, int duration) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.director = director;
        this.duration = duration;
    }
}
