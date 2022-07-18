package repository;

import model.Film;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {FilmRepository.class})
public class FilmRepositoryTest {

    @Autowired
    FilmRepository filmRepository;

    private Set<Film> films;

    private final Film film1 = new Film("1546ADRT", "title 1", new ArrayList<>(List.of("sf")), "George Lucas", LocalDateTime.now());
    private final Film film2 = new Film("179879-Gk", "title 2", new ArrayList<>(List.of("sf", "love")), "Martin Scorsese", LocalDateTime.now());
    private final Film film3 = new Film("76GBHB", "title 3", new ArrayList<>(List.of("sf", "action")), "Nolan", LocalDateTime.now());

    @BeforeEach
    void setup() {
        filmRepository = new FilmRepository();

        films = new HashSet<>(new ArrayList<>(List.of(
                film1,
                film2,
                film3
        )));
    }

    @Test
    void should_add_video_in_memory() {
        //Given
        //When
        filmRepository.add(film1);
        //Then
        Assertions.assertIterableEquals(List.of(film1), filmRepository.getAll());
    }

    @Test
    void should_get_by_id() throws Exception {
        //Given
        Film inMemoryFilm;
        String id = "179879-Gk";
        filmRepository.add(film2);
        //When
        Optional<Film> filmOptional = filmRepository.getById(id);
        if (filmOptional.isPresent()) {
            inMemoryFilm = filmOptional.get();
        } else {
            throw new Exception("");
        }
        //Then
        Assertions.assertEquals(film2, inMemoryFilm);
    }

    @Test
    void should_get_all_videos() {
        //Given
        films.forEach(film -> filmRepository.add(film));
        //When
        Set<Film> inMemoryFilms = filmRepository.getAll();
        //Then
        Assertions.assertIterableEquals(inMemoryFilms, films);
    }
}
