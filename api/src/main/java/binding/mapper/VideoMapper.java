package binding.mapper;

import binding.dto.VideoDto;
import model.Video;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class VideoMapper {

    public Video toModel(VideoDto videoDto) {
        return new Video(videoDto.getId(), videoDto.getTitle(), videoDto.getLabels());
    }

    public VideoDto toDto(Video video) {
        return new VideoDto(video.getId(), video.getTitle(), video.getLabels());
    }

    public Set<VideoDto> toDtoSet(Set<Video> videos) {
        return videos.stream().map(this::toDto).collect(Collectors.toSet());
    }
}

