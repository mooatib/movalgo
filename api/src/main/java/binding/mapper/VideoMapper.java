package binding.mapper;

import binding.dto.VideoDto;
import model.Video;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class VideoMapper {

    /**
     * Returns a Video object that has been mapped from a Video Data Transfer Object
     * @param videoDto   the DTO that will be mapped
     * @return          the core model that has been mapped from the DTO
     */
    public Video toModel(VideoDto videoDto) {
        return new Video(videoDto.getId(), videoDto.getTitle(), videoDto.getLabels());
    }

    /**
     * Returns a Video Data Transfer Object that has been mapped from a Video object
     * @param video   the core model that will be mapped
     * @return       the DTO that has been mapped from the core model
     */
    public VideoDto toDto(Video video) {
        return new VideoDto(video.getId(), video.getTitle(), video.getLabels());
    }

    /**
     * Returns a Set of Video Data Transfer Object that has been mapped from a Set of Video object
     * @param videos  the set of core models that will be mapped
     * @return       the set of DTOs that has been mapped from the set of core model
     */
    public Set<VideoDto> toDtoSet(Set<Video> videos) {
        return videos.stream().map(this::toDto).collect(Collectors.toSet());
    }
}

