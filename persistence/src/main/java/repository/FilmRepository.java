package repository;

import model.Film;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class FilmRepository {

    private static final Set<Film> inMemoryFilms = new HashSet<>();

    /**
     * @param film
     */
    public void add(Film film) {
        inMemoryFilms.add(film);
    }

    /**
     * @return Set<Video>
     */
    public Set<Film> getAll() {
        return inMemoryFilms;
    }

    /**
     * @return int
     */
    public int getSize() {
        return inMemoryFilms.size();
    }
}
