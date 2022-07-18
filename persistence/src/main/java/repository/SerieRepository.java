package repository;

import model.Film;
import model.Serie;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class SerieRepository {

    private static final Set<Serie> inMemorySeries = new HashSet<>();

    /**
     * @param serie
     * @return
     */
    public Serie add(Serie serie) {
        inMemorySeries.add(serie);
        return serie;
    }

    /**
     * @return Set<Video>
     */
    public Set<Serie> getAll() {
        return inMemorySeries;
    }

    public Optional<Serie> getById(String id) {
        return inMemorySeries.stream().filter(inMemorySerie -> inMemorySerie.getId().equals(id)).findFirst();
    }

    public void delete(Serie serie) {
        inMemorySeries.remove(serie);
    }
    /**
     * @return int
     */
    public int getSize() {
        return inMemorySeries.size();
    }
}
