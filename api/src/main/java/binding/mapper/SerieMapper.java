package binding.mapper;

import binding.dto.SerieDto;
import model.Serie;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SerieMapper {

    /**
     * Returns a Serie object that has been mapped from a Serie Data Transfer Object
     * @param serieDto   the DTO that will be mapped
     * @return          the core model that has been mapped from the DTO
     */
    public Serie toModel(SerieDto serieDto) {
        return new Serie(serieDto.getTitle(), serieDto.getLabels(), serieDto.getNumberOfEpisodes());
    }

    /**
     * Returns a Serie Data Transfer Object that has been mapped from a Serie object
     * @param serie   the core model that will be mapped
     * @return       the DTO that has been mapped from the core model
     */
    public SerieDto toDto(Serie serie) {
        return new SerieDto(serie.getId(), serie.getTitle(), serie.getLabels(), serie.getNumberOfEpisodes());
    }

    /**
     * Returns a Set of Serie Data Transfer Object that has been mapped from a Set of Serie object
     * @param series  the set of core models that will be mapped
     * @return       the set of DTOs that has been mapped from the set of core model
     */
    public Set<SerieDto> toDtoSet(Set<Serie> series) {
        return series.stream().map(this::toDto).collect(Collectors.toSet());
    }
}

