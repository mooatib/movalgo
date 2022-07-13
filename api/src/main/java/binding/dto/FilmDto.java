package binding.dto;

import java.time.LocalDateTime;
import java.util.List;

public class FilmDto extends VideoDto {
    private String director;
    private LocalDateTime releaseDate;

    public FilmDto(String id, String title, List<String> labels, String director, LocalDateTime releaseDate) {
        super(id, title, labels);
        this.director = director;
        this.releaseDate = releaseDate;
    }

    public FilmDto(){

    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }
}
