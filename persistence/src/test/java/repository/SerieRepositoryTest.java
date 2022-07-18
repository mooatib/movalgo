package repository;

import model.Serie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {SerieRepository.class})
public class SerieRepositoryTest {

    @Autowired
    SerieRepository serieRepository;

    private Set<Serie> series;

    private final Serie serie1 = new Serie("1546ADRT", "title 1", new ArrayList<>(List.of("sf")), 10);
    private final Serie serie2 = new Serie("179879-Gk", "title 2", new ArrayList<>(List.of("sf", "love")), 2);
    private final Serie serie3 = new Serie("76GBHB", "title 3", new ArrayList<>(List.of("sf", "action")), 178);

    @BeforeEach
    void setup() {
        serieRepository = new SerieRepository();

        series = new HashSet<>(new ArrayList<>(List.of(
                serie1,
                serie2,
                serie3
        )));
    }

    @Test
    void should_add_video_in_memory() {
        //Given
        //When
        serieRepository.add(serie1);
        //Then
        Assertions.assertIterableEquals(List.of(serie1), serieRepository.getAll());
    }

    @Test
    void should_get_by_id() throws Exception {
        //Given
        Serie inMemorySerie;
        String id = "179879-Gk";
        serieRepository.add(serie2);
        //When
        Optional<Serie> serieOptional = serieRepository.getById(id);
        if (serieOptional.isPresent()) {
            inMemorySerie = serieOptional.get();
        } else {
            throw new Exception("");
        }
        //Then
        Assertions.assertEquals(serie2, inMemorySerie);
    }

    @Test
    void should_get_all_videos() {
        //Given
        series.forEach(serie -> serieRepository.add(serie));
        //When
        Set<Serie> inMemorySeries = serieRepository.getAll();
        //Then
        Assertions.assertIterableEquals(inMemorySeries, series);
    }
}
