package service;

import exception.VideoException;
import model.Video;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.VideoRepository;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

    @Mock
    VideoRepository videoRepository;

    @InjectMocks
    VideoService videoService;


    @Test
    void should_add_video_in_memory() throws VideoException.VideoExists {
        //Given
        Video video1 = new Video("1546ADRT", "title 1", new ArrayList<>(List.of("sf")));
        //When
        when(videoRepository.add(video1)).thenReturn(video1);
        //Then
        Assertions.assertEquals(video1, videoService.add(video1));
    }

    @Test
    void should_get_by_id() throws Exception {
        //Given
        Video video1 = new Video("1546ADRT", "title 1", new ArrayList<>(List.of("sf")));
        //When
        when(videoRepository.getById(video1.getId())).thenReturn(Optional.of(video1));
        //Then
        Assertions.assertEquals(video1, videoService.getById(video1.getId()));
    }

    @Test
    void should_get_by_title() {
        //Given
        Video video1 = new Video("1546ADRT", "title 1", new ArrayList<>(List.of("sf")));
        //When
        when(videoRepository.getByTitle(video1.getTitle())).thenReturn(Set.of(video1));
        //Then
        Assertions.assertEquals(Set.of(video1), videoService.getByTitle(video1.getTitle()));
    }

    @Test
    void should_get_similar_videos() {
        //Given
        Video video1 = new Video("1546ADRT", "title 1", new ArrayList<>(List.of("sf")));
        Video video2 = new Video("1GJG1", "title 2", new ArrayList<>(List.of("sf", "action")));
        Video video3 = new Video("1JKLK7897", "title 3", new ArrayList<>(List.of("sf", "action", "love")));

        Set<Video> similar = new HashSet<>(List.of(video2, video3));
        //When
        when(videoRepository.getSimilar(video2, 2)).thenReturn(similar);
        //Then
        Assertions.assertEquals(similar, videoService.getSimilar(video2, 2));
    }

    @Test
    void should_get_all_videos() {
        //Given
        Video video1 = new Video("1546ADRT", "title 1", new ArrayList<>(List.of("sf")));
        Video video2 = new Video("1GJG1", "title 2", new ArrayList<>(List.of("sf", "action")));
        Video video3 = new Video("1JKLK7897", "title 3", new ArrayList<>(List.of("sf", "action", "love")));

        Set<Video> videos = new HashSet<>(List.of(video1, video2, video3));
        //When
        when(videoRepository.getAll()).thenReturn(videos);
        //Then
        Assertions.assertEquals(videos, videoService.getAll());
    }

    @Test
    void should_get_all_deleted_videos() throws Exception {
        //Given
        Video video1 = new Video("1546ADRT", "title 1", new ArrayList<>(List.of("sf")));
        Video video2 = new Video("1GJG1", "title 2", new ArrayList<>(List.of("sf", "action")));
        Video video3 = new Video("1JKLK7897", "title 3", new ArrayList<>(List.of("sf", "action", "love")));

        Set<Video> deletedVideos = new HashSet<>(List.of(video1, video2, video3));
        //When
        when(videoRepository.getAllDeleted()).thenReturn(deletedVideos);
        //Then
        Assertions.assertEquals(deletedVideos, videoService.getAllDeleted());
    }

    @Test
    void should_delete_by_id() throws Exception {
        //Given
        Video video1 = new Video("1546ADRT", "title 1", new ArrayList<>(List.of("sf")));
        //When
        when(videoRepository.getById(video1.getId())).thenReturn(Optional.of(video1));
        when(videoRepository.delete(video1)).thenReturn(video1);
        //Then
        Assertions.assertEquals(video1, videoService.delete(video1.getId()));
    }


}
