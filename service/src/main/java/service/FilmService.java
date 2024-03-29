package service;

import exception.FilmException;
import model.Film;
import model.Video;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import repository.FilmRepository;
import repository.VideoRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final VideoRepository videoRepository;

    public FilmService(FilmRepository filmRepository, VideoRepository videoRepository) {
        this.filmRepository = filmRepository;
        this.videoRepository = videoRepository;
    }

    public Film add(Film film) throws FilmException.FilmExists {
        Set<Video> videos = videoRepository.getByTitle(film.getTitle());
        if (!videos.isEmpty()) {
            throw new FilmException.FilmExists();
        }
        film.setId(String.valueOf(videoRepository.getSize() + videoRepository.getAllDeleted().size() + 1));
        videoRepository.add(film);
        LoggerFactory.getLogger(getClass()).info("A film has been created");
        return filmRepository.add(film);
    }

    public Set<Film> getAll() {
        return filmRepository.getAll();
    }

    public Film getById(String id) throws Exception {
        Optional<Film> filmOptional = filmRepository.getById(id);
        if (filmOptional.isPresent()) {
            return filmOptional.get();
        } else throw new FilmException.FilmNotFound();
    }
}
