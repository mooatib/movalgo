package service;

import model.Video;
import org.springframework.stereotype.Service;
import repository.VideoRepository;

import java.util.Set;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public void add(Video video) {
        video.setId(videoRepository.getSize() + 1);
        videoRepository.add(video);
    }

    public Video getById(int id) throws Exception {
        return videoRepository.getById(id);
    }

    public Set<Video> getByTitle(String filter) {
        return videoRepository.getByTitle(filter);
    }

    public Set<Video> getSimilar(Video video, int numberOfMatch) {
        return videoRepository.getSimilar(video, numberOfMatch);
    }

    public Set<Video> getAll() {
        return videoRepository.getAll();
    }

    public Set<Video> getAllDeleted() {
        return videoRepository.getAllDeleted();
    }

    public void delete(int id) throws Exception {
        videoRepository.delete(id);
    }
}
