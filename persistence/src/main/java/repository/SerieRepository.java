package repository;

import model.Serie;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SerieRepository {

    private static final Set<Serie> inMemorySeries = new HashSet<>();

    /**
     * @param serie
     */
    public void add(Serie serie) {
        inMemorySeries.add(serie);
    }

    /**
     * @return Set<Video>
     */
    public Set<Serie> getAll() {
        return inMemorySeries;
    }

    /**
     * @return int
     */
    public int getSize() {
        return inMemorySeries.size();
    }
}
