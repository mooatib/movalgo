package repository;

import model.Film;
import model.Serie;
import model.Video;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class FilmRepository {

    private static final Set<Film> inMemoryFilms = new HashSet<>();

    /**
     * @param film
     */
    public Film add(Film film) {
        inMemoryFilms.add(film);
        return film;
    }

    /**
     * @return Set<Video>
     */
    public Set<Film> getAll() {
        return inMemoryFilms;
    }

    public Optional<Film> getById(String id) {
        return inMemoryFilms.stream().filter(inMemoryFilm -> inMemoryFilm.getId().equals(id)).findFirst();
    }

    public void delete(Film film) {
        inMemoryFilms.remove(film);
    }

    /**
     * @return int
     */
    public int getSize() {
        return inMemoryFilms.size();
    }
}
