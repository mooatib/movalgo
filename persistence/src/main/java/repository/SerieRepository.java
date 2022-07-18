package repository;

import model.Serie;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class SerieRepository {

    private static final Set<Serie> inMemorySeries = new HashSet<>();

    /**
     * This method add a Serie in memory
     *
     * @param serie the Serie that will be added in memory
     * @return the Serie that has been added
     */
    public Serie add(Serie serie) {
        inMemorySeries.add(serie);
        return serie;
    }

    /**
     * This method retrieves a Set of all Series from memory
     *
     * @return the Set of all Series in memory
     */
    public Set<Serie> getAll() {
        return inMemorySeries;
    }

    /**
     * This method retrieves a Serie from memory base on it's id
     *
     * @param id the Serie's id that will be retrieved
     * @return the Serie from memory
     */
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
