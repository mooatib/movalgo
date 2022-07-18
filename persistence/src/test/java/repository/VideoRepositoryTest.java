package repository;

import model.Video;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {VideoRepository.class, FilmRepository.class, SerieRepository.class})
public class VideoRepositoryTest {

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    SerieRepository serieRepository;

    private Set<Video> videos;

    private final Video video1 = new Video("1546ADRT", "title 1", new ArrayList<>(List.of("sf")));
    private final Video video2 = new Video("179879-Gk", "title 2", new ArrayList<>(List.of("sf", "action")));
    private final Video video3 = new Video("76GBHB", "title 3", new ArrayList<>(List.of("sf", "action", "horror")));
    private final Video video4 = new Video("687BGJH", "title 4", new ArrayList<>(List.of("sf", "action", "horror", "funny")));
    private final Video video6 = new Video("HJIAH78798", "test", new ArrayList<>(List.of("sf", "action", "horror", "funny")));

    @BeforeEach
    void setup() {
        videoRepository = new VideoRepository(filmRepository, serieRepository);

        videos = new HashSet<>(List.of(
                video1,
                video2,
                video3,
                video4,
                video6
        ));
    }

    @Test
    void should_add_video_in_memory() {
        //Given
        //When
        videoRepository.add(video1);
        //Then
        Assertions.assertIterableEquals(List.of(video1), videoRepository.getAll());
    }

    @Test
    void should_get_by_id() throws Exception {
        //Given
        Video inMemoryVideo;
        String id = "179879-Gk";
        videoRepository.add(video2);
        //When
        Optional<Video> videoOptional = videoRepository.getById(id);
        if (videoOptional.isPresent()) {
            inMemoryVideo = videoOptional.get();
        } else {
            throw new Exception("");
        }
        //Then
        Assertions.assertEquals(video2, inMemoryVideo);
    }

    @Test
    void should_get_by_title() {
        //Given
        String filter = "tit";
        videos.forEach(video -> videoRepository.add(video));
        //When
        Set<Video> inMemoryVideos = videoRepository.getByTitle(filter);
        //Then
        Assertions.assertIterableEquals(List.of(video1, video2, video3, video4), inMemoryVideos);
    }

    @Test
    void should_get_similar_videos() {
        //Given
        videos.forEach(video -> videoRepository.add(video));
        Video video = new Video("5GHJHGBDIJ", "title 7", new ArrayList<>(List.of("sf", "action", "horror", "funny")));
        int numberOfMatch = 3;

        //When
        Set<Video> inMemoryVideos = videoRepository.getSimilar(video, numberOfMatch);
        //Then
        Assertions.assertIterableEquals(List.of(video6, video3, video4), inMemoryVideos);
    }

    @Test
    void should_get_all_videos() {
        //Given
        videos.forEach(video -> videoRepository.add(video));
        //When
        Set<Video> inMemoryVideos = videoRepository.getAll();
        //Then
        Assertions.assertIterableEquals(inMemoryVideos, videos);
    }

    @Test
    void should_get_all_deleted_videos() throws Exception {
        //Given
        videos.forEach(video -> videoRepository.add(video));
        videoRepository.delete(video2);
        //When
        Set<Video> inMemoryDeletedVideos = videoRepository.getAllDeleted();
        //Then
        Assertions.assertIterableEquals(List.of(video2), inMemoryDeletedVideos);
    }

    @Test
    void should_delete_by_id() throws Exception {
        //Given
        videos.forEach(video -> videoRepository.add(video));
        //When
        videoRepository.delete(video2);
        //Then
        Assertions.assertIterableEquals(List.of(video6, video1, video3, video4), videoRepository.getAll());
        Assertions.assertIterableEquals(List.of(video2), videoRepository.getAllDeleted());
    }
}
