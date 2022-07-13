package repository;

import model.Video;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class VideoRepository {

    private static final Set<Video> inMemoryVideos = new HashSet<>();
    private static final Set<Video> inMemoryDeletedVideos = new HashSet<>();

    /**
     * @param video
     */
    public void add(Video video) {
        inMemoryVideos.add(video);
    }

    /**
     * @param id
     * @return Video
     * @throws Exception
     */
    public Video getById(String id) throws Exception {
        Optional<Video> videoOptional = inMemoryVideos.stream().filter(inMemoryVideo -> inMemoryVideo.getId() == id).findFirst();
        if (videoOptional.isPresent()) {
            return videoOptional.get();
        } else throw new Exception("");
    }

    /**
     * @param filter
     * @return Set<Video>
     */
    public Set<Video> getByTitle(String filter) {
        return inMemoryVideos.stream().filter(inMemoryVideo -> inMemoryVideo.getTitle().contains(filter)).collect(Collectors.toSet());
    }

    /**
     * @param video
     * @param numberOfMatch
     * @return Set<Video>
     */
    public Set<Video> getSimilar(Video video, int numberOfMatch) {
        return inMemoryVideos.stream()
                .filter(inMemoryVideo -> {
                    Set<String> matchedLabels = new HashSet<>();
                    video.getLabels().forEach(label -> {
                        if (inMemoryVideo.getLabels().contains(label)) {
                            matchedLabels.add(label);
                        }
                    });
                    return matchedLabels.size() >= numberOfMatch;
                })
                .collect(Collectors.toSet());
    }

    /**
     * @return Set<Video>
     */
    public Set<Video> getAll() {
        return inMemoryVideos;
    }

    /**
     * @return Set<Video>
     */
    public Set<Video> getAllDeleted() {
        return inMemoryDeletedVideos;
    }

    /**
     * @param id
     * @throws Exception
     */
    public void delete(String id) throws Exception {
        Optional<Video> videoOptional = inMemoryVideos.stream().filter(inMemoryVideo -> inMemoryVideo.getId() == id).findAny();
        if (videoOptional.isPresent()) {
            Video video = videoOptional.get();
            inMemoryVideos.remove(videoOptional.get());
            inMemoryDeletedVideos.add(videoOptional.get());
        } else throw new Exception("");
    }

    /**
     * @return int
     */
    public int getSize() {
        return inMemoryVideos.size();
    }
}
