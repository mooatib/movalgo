package service;

import model.Serie;
import org.springframework.stereotype.Service;
import repository.SerieRepository;
import repository.VideoRepository;

import java.util.Set;

@Service
public class SerieService {

    private final SerieRepository serieRepository;
    private final VideoRepository videoRepository;

    public SerieService(SerieRepository serieRepository, VideoRepository videoRepository) {
        this.serieRepository = serieRepository;
        this.videoRepository = videoRepository;
    }

    public void add(Serie serie) {
        serie.setId(String.valueOf(videoRepository.getSize() + 1));
        serieRepository.add(serie);
        videoRepository.add(serie);
    }

    public Set<Serie> getAll() {
        return serieRepository.getAll();
    }
}
