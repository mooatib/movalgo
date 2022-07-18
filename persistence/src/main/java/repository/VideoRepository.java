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
     * This method add a Video in memory
     * @param video  the Video that will be added in memory
     * @return      the Video that has been added
     */
    public Video add(Video video) {
        inMemoryVideos.add(video);
        return video;
    }

    /**
     * This method retrieves a Video from memory base on it's id
     *
     * @param id the Video id that will be retrieved
     * @return the Video from memory
     */
    public Optional<Video> getById(String id) {
        return inMemoryVideos.stream().filter(inMemoryVideo -> inMemoryVideo.getId().equals(id)).findFirst();
    }

    /**
     * This method retrieves a Set of Videos from memory based on a filter
     * @param filter    the filter that will be searched among the Videos title
     * @return          the Set of Videos from memory that contains the filter in their title
     */
    public Set<Video> getByTitle(String filter) {
        return inMemoryVideos.stream().filter(inMemoryVideo -> inMemoryVideo.getTitle().contains(filter)).collect(Collectors.toSet());
    }

    /**
     * This method retrieves similar Videos based on another
     * @param video             the Video that will be used in order to search similar videos
     * @param numberOfMatch     the number of expected matched labels
     * @return                  a Set of similar Videos
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
     * This method retrieves a Set of all Videos from memory
     * @return          the Set of all Videos in memory
     */
    public Set<Video> getAll() {
        return inMemoryVideos;
    }

    /**
     * This method retrieves a Set of all deleted Videos from memory
     * @return          the Set of all deleted Videos in memory
     */
    public Set<Video> getAllDeleted() {
        return inMemoryDeletedVideos;
    }

    /**
     * This method deletes a Video from memory base on another
     * @param video     the Video that will be deleted
     * @return          the deleted Video
     */
    public Video delete(Video video) {
            filmRepository.getById(video.getId()).ifPresent(filmRepository::delete);
            serieRepository.getById(video.getId()).ifPresent(serieRepository::delete);
            inMemoryVideos.remove(video);
            inMemoryDeletedVideos.add(video);
            return video;
    }

    public int getSize() {
        return inMemoryVideos.size();
    }
}
