package ch.wiss.m223.MovieWatchlist.services;

import ch.wiss.m223.MovieWatchlist.model.Role;
import ch.wiss.m223.MovieWatchlist.model.User;
import ch.wiss.m223.MovieWatchlist.model.UserDetailsImpl;
import ch.wiss.m223.MovieWatchlist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        List<GrantedAuthority> authorities = new ArrayList<>(); // important role to authorities mapping
        for (Role r : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getName()));
        }
        return UserDetailsImpl.build(user);
    }
}