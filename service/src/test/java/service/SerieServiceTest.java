package service;

import exception.SerieException;
import model.Serie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.SerieRepository;
import repository.VideoRepository;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SerieServiceTest {

    @Mock
    SerieRepository serieRepository;

    @Mock
    VideoRepository videoRepository;

    @InjectMocks
    SerieService serieService;


    @Test
    void should_add_serie_in_memory() throws SerieException.SerieExists {
        //Given
        Serie serie1 = new Serie("1546ADRT", "title 1", new ArrayList<>(List.of("sf")), 10);
        //When
        when(serieRepository.add(serie1)).thenReturn(serie1);
        //Then
        Assertions.assertEquals(serie1, serieService.add(serie1));
    }

    @Test
    void should_get_by_id() throws Exception {
        //Given
        Serie serie1 = new Serie("1546ADRT", "title 1", new ArrayList<>(List.of("sf")), 10);
        //When
        when(serieRepository.getById(serie1.getId())).thenReturn(Optional.of(serie1));
        //Then
        Assertions.assertEquals(serie1, serieService.getById(serie1.getId()));
    }

    @Test
    void should_get_all_series() {
        //Given
        Serie serie1 = new Serie("1546ADRT", "title 1", new ArrayList<>(List.of("sf")), 10);
        Serie serie2 = new Serie("1546ADRT", "title 1", new ArrayList<>(List.of("sf", "action")), 29);
        Serie serie3 = new Serie("1546ADRT", "title 1", new ArrayList<>(List.of("sf", "action", "love")), 1809);

        Set<Serie> series = new HashSet<>(List.of(serie1, serie2, serie3));
        //When
        when(serieRepository.getAll()).thenReturn(series);
        //Then
        Assertions.assertEquals(series, serieService.getAll());
    }
}
