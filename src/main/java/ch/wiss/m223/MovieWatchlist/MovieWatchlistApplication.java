package ch.wiss.m223.MovieWatchlist;

import ch.wiss.m223.MovieWatchlist.repositories.RoleRepository;
import ch.wiss.m223.MovieWatchlist.model.ERole;
import ch.wiss.m223.MovieWatchlist.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MovieWatchlistApplication implements CommandLineRunner{
	@Autowired
	RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(MovieWatchlistApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		if (roleRepository.count() == 0) {
			roleRepository.save(new Role(ERole.ROLE_USER));
			roleRepository.save(new Role(ERole.ROLE_ADMIN));
		}
	}
}
