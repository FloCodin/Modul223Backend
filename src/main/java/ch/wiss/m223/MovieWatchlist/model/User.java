package ch.wiss.m223.MovieWatchlist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public User(String name, String email, String password) {
        this.username = name;
        this.email = email;
        this.password = password;
    }
    @ManyToMany(fetch = FetchType.LAZY) // N:M Mapping
    private Set<Role> roles = new HashSet<>();
}