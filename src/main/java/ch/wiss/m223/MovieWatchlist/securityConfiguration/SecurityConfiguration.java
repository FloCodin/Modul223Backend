package ch.wiss.m223.MovieWatchlist.securityConfiguration;

import ch.wiss.m223.MovieWatchlist.security.AuthTokenFilter;
import ch.wiss.m223.MovieWatchlist.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    //@Autowired private AuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


    private static final String[] EVERYONE = {"/public", "/api/auth/*",};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http.csrf(csrf -> csrf.disable()) //disable Cross-Site Request Forgery (CSRF) prevention
                .cors(Customizer.withDefaults()) //configure CORS: Cross Origin Request Sharing
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST, "/items").hasRole("ADMIN");
                    auth.requestMatchers(EVERYONE).permitAll()
                            .requestMatchers("/api/tmdb/**").permitAll()
                            .anyRequest().authenticated();
                })
                //.formLogin(Customizer.withDefaults())  //für Login-Form im Browser
              ; // für CURL, Postman, Insomnia
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") .allowedOrigins("http://localhost:5173");
            }
        };
    }

}