package binding.mapper;

import binding.dto.FilmDto;
import model.Film;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmMapper {


    /**
     * Returns a Film object that has been mapped from a Film Data Transfer Object
     * @param filmDto   the DTO that will be mapped
     * @return          the core model that has been mapped from the DTO
     */
    public Film toModel(FilmDto filmDto) {
        return new Film(filmDto.getTitle(), filmDto.getLabels(), filmDto.getDirector(), filmDto.getReleaseDate());
    }

    /**
     * Returns a Film Data Transfer Object that has been mapped from a Film object
     * @param film   the core model that will be mapped
     * @return       the DTO that has been mapped from the core model
     */
    public FilmDto toDto(Film film) {
        return new FilmDto(film.getId(), film.getTitle(), film.getLabels(), film.getDirector(), film.getReleaseDate());
    }

    /**
     * Returns a Set of Film Data Transfer Object that has been mapped from a Set of Film object
     * @param films  the set of core models that will be mapped
     * @return       the set of DTOs that has been mapped from the set of core model
     */
    public Set<FilmDto> toDtoSet(Set<Film> films) {
        return films.stream().map(this::toDto).collect(Collectors.toSet());
    }
}

