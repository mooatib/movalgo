package repository;

import model.Film;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class FilmRepository {

    private static final Set<Film> inMemoryFilms = new HashSet<>();

    /**
     * This method add a film in memory
     * @param film  the Film that will be added in memory
     * @return      the Film that has been added
     */
    public Film add(Film film) {
        inMemoryFilms.add(film);
        return film;
    }

    /**
     * This method retrieves a Set of all Films from memory
     * @return          the Set of all Films in memory
     */
    public Set<Film> getAll() {
        return inMemoryFilms;
    }

    /**
     * This method retrieves a Film from memory base on it's id
     *
     * @param id the Film's id that will be retrieved
     * @return the Film from memory
     */
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
