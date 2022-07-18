package service;

import exception.SerieException;
import model.Serie;
import model.Video;
import org.springframework.stereotype.Service;
import repository.SerieRepository;
import repository.VideoRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class SerieService {

    private final SerieRepository serieRepository;
    private final VideoRepository videoRepository;

    public SerieService(SerieRepository serieRepository, VideoRepository videoRepository) {
        this.serieRepository = serieRepository;
        this.videoRepository = videoRepository;
    }

    public Serie add(Serie serie) throws SerieException.SerieExists {
        Set<Video> videos = videoRepository.getByTitle(serie.getTitle());
        if (!videos.isEmpty()) {
            throw new SerieException.SerieExists();
        }
        serie.setId(String.valueOf(videoRepository.getSize() + videoRepository.getAllDeleted().size() + 1));
        videoRepository.add(serie);
        return serieRepository.add(serie);
    }

    public Set<Serie> getAll() {
        return serieRepository.getAll();
    }

    public Serie getById(String id) throws Exception {
        Optional<Serie> serieOptional = serieRepository.getById(id);
        if (serieOptional.isPresent()) {
            return serieOptional.get();
        } else throw new SerieException.SerieNotFound();
    }
}
