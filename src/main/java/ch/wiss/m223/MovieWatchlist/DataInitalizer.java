package ch.wiss.m223.MovieWatchlist;

import ch.wiss.m223.MovieWatchlist.model.Movie;
import ch.wiss.m223.MovieWatchlist.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
class DataInitializer implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void run(String... args) {
        if (movieRepository.count() == 0) {
            movieRepository.save(new Movie(
                    "Inception",
                    "Traum im Traum",
                    2010,
                    "Christopher Nolan",
                    148
            ));
            movieRepository.save(new Movie(
                    "Interstellar",
                    "Raum-Zeit-Reise",
                    2014,
                    "Christopher Nolan",
                    169
            ));
            movieRepository.save(new Movie(
                    "Star Wars: Episode I – Die dunkle Bedrohung",
                    "Junge Anakin wird entdeckt – der Anfang der Macht",
                    1999,
                    "George Lucas",
                    136
            ));
            movieRepository.save(new Movie(
                    "Star Wars: Episode II – Angriff der Klonkrieger",
                    "Die Republik steht vor dem Umbruch",
                    2002,
                    "George Lucas",
                    142
            ));
            movieRepository.save(new Movie(
                    "Star Wars: Episode III – Die Rache der Sith",
                    "Anakins Fall zur dunklen Seite",
                    2005,
                    "George Lucas",
                    140
            ));
        }
    }
}
