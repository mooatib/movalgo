package binding.mapper;

import binding.dto.FilmDto;
import model.Film;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmMapper {

    public Film toModel(FilmDto filmDto) {
        return new Film(filmDto.getTitle(), filmDto.getLabels(), filmDto.getDirector(), filmDto.getReleaseDate());
    }

    public FilmDto toDto(Film film) {
        return new FilmDto(film.getId(), film.getTitle(), film.getLabels(), film.getDirector(), film.getReleaseDate());
    }

    public Set<FilmDto> toDtoSet(Set<Film> films) {
        return films.stream().map(this::toDto).collect(Collectors.toSet());
    }
}

