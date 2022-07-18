package repository;

import model.Video;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class VideoRepository {


    private final FilmRepository filmRepository;

    private final SerieRepository serieRepository;

    private final Set<Video> inMemoryVideos = new HashSet<>();

    private final Set<Video> inMemoryDeletedVideos = new HashSet<>();

    public VideoRepository(FilmRepository filmRepository, SerieRepository serieRepository) {
        this.filmRepository = filmRepository;
        this.serieRepository = serieRepository;
    }

    /**
     * @param video
     */
    public Video add(Video video) {
        inMemoryVideos.add(video);
        return video;
    }

    /**
     * @param id
     * @return Video
     * @throws Exception
     */
    public Optional<Video> getById(String id) {
        return inMemoryVideos.stream().filter(inMemoryVideo -> inMemoryVideo.getId().equals(id)).findFirst();
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
     * @param video
     */
    public Video delete(Video video) {
            filmRepository.getById(video.getId()).ifPresent(filmRepository::delete);
            serieRepository.getById(video.getId()).ifPresent(serieRepository::delete);
            inMemoryVideos.remove(video);
            inMemoryDeletedVideos.add(video);
            return video;
    }

    /**
     * @return int
     */
    public int getSize() {
        return inMemoryVideos.size();
    }
}
