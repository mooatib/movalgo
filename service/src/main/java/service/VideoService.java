package service;

import exception.VideoException;
import model.Video;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import repository.VideoRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video add(Video video) throws VideoException.VideoExists {
        Set<Video> videos = videoRepository.getByTitle(video.getTitle());
        if (!videos.isEmpty()) {
            throw new VideoException.VideoExists();
        }
        video.setId(String.valueOf(videoRepository.getSize() + 1));
        Video inMemoryVideo = videoRepository.add(video);
        LoggerFactory.getLogger(getClass()).info("A video has been created");
        return inMemoryVideo;
    }

    public Video getById(String id) throws VideoException.VideoNotFound {
        Optional<Video> videoOptional = videoRepository.getById(id);
        if (videoOptional.isPresent()) {
            return videoOptional.get();
        } else throw new VideoException.VideoNotFound();
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

    public void delete(String id) throws Exception {
        Video video = getById(id);
        videoRepository.delete(video);
    }
}
