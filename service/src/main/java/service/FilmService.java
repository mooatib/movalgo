package service;

import model.Film;
import org.springframework.stereotype.Service;
import repository.FilmRepository;
import repository.VideoRepository;

import java.util.Set;

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final VideoRepository videoRepository;

    public FilmService(FilmRepository filmRepository, VideoRepository videoRepository) {
        this.filmRepository = filmRepository;
        this.videoRepository = videoRepository;
    }

    public void add(Film film) {
        film.setId(videoRepository.getSize() + 1);
        filmRepository.add(film);
        videoRepository.add(film);
    }

    public Set<Film> getAll() {
        return filmRepository.getAll();
    }
}
