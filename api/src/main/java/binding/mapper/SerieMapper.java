package binding.mapper;

import binding.dto.SerieDto;
import model.Serie;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SerieMapper {

    public Serie toModel(SerieDto serieDto) {
        return new Serie(serieDto.getTitle(), serieDto.getLabels(), serieDto.getNumberOfEpisodes());
    }

    public SerieDto toDto(Serie serie) {
        return new SerieDto(serie.getId(), serie.getTitle(), serie.getLabels(), serie.getNumberOfEpisodes());
    }

    public Set<SerieDto> toDtoSet(Set<Serie> series) {
        return series.stream().map(this::toDto).collect(Collectors.toSet());
    }
}

