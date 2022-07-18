package service;

import exception.FilmException;
import model.Film;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.FilmRepository;
import repository.VideoRepository;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FilmServiceTest {

    @Mock
    FilmRepository filmRepository;

    @Mock
    VideoRepository videoRepository;

    @InjectMocks
    FilmService filmService;


    @Test
    void should_add_film_in_memory() throws FilmException.FilmExists {
        //Given
        Film film1 = new Film("1546ADRT", "title 1", new ArrayList<>(List.of("sf")), "George Lucas", LocalDateTime.now());
        //When
        when(filmRepository.add(film1)).thenReturn(film1);
        //Then
        Assertions.assertEquals(film1, filmService.add(film1));
    }

    @Test
    void should_get_by_id() throws Exception {
        //Given
        Film film1 = new Film("1546ADRT", "title 1", new ArrayList<>(List.of("sf")), "George Lucas", LocalDateTime.now());
        //When
        when(filmRepository.getById(film1.getId())).thenReturn(Optional.of(film1));
        //Then
        Assertions.assertEquals(film1, filmService.getById(film1.getId()));
    }

    @Test
    void should_get_all_films() {
        //Given
        Film film1 = new Film("1546ADRT", "title 1", new ArrayList<>(List.of("sf")), "George Lucas", LocalDateTime.now());
        Film film2 = new Film("1546ADRT", "title 1", new ArrayList<>(List.of("sf", "action")), "Nolan", LocalDateTime.now());
        Film film3 = new Film("1546ADRT", "title 1", new ArrayList<>(List.of("sf", "action", "love")), "Scorsese", LocalDateTime.now());

        Set<Film> films = new HashSet<>(List.of(film1, film2, film3));
        //When
        when(filmRepository.getAll()).thenReturn(films);
        //Then
        Assertions.assertEquals(films, filmService.getAll());
    }
}
