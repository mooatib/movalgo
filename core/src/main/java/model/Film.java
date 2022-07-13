package model;

import java.time.LocalDateTime;
import java.util.List;

public class Film extends Video {
    private final String director;
    private final LocalDateTime releaseDate;

    public Film(String title, List<String> labels, String director, LocalDateTime releaseDate) {
        super(title, labels);
        this.director = director;
        this.releaseDate = releaseDate;
    }

    public Film(String id, String title, List<String> labels, String director, LocalDateTime releaseDate) {
        super(id, title, labels);
        this.director = director;
        this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "Film{" +
                "director='" + director + '\'' +
                ", releaseDate=" + releaseDate +
                "} " + super.toString();
    }
}
