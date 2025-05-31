package ch.wiss.m223.MovieWatchlist.controller;

import ch.wiss.m223.MovieWatchlist.dto.RatingDTO;
import ch.wiss.m223.MovieWatchlist.security.JwtUtils;
import ch.wiss.m223.MovieWatchlist.services.RatingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RatingController.class)
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    @MockBean
    private JwtUtils jwtUtils;

    @BeforeEach
    void setup() {
        when(jwtUtils.validateToken(any())).thenReturn(true);
        when(jwtUtils.extractUsername(any())).thenReturn("user");
    }

    @Test
    void shouldCreateRating() throws Exception {
        RatingDTO dto = new RatingDTO();
        dto.setMovieId(123L);
        dto.setScore(4);
        dto.setComment("Guter Film");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/api/ratings")
                        .header("Authorization", "Bearer dummy-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}