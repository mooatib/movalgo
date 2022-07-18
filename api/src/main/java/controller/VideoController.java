package controller;

import binding.dto.VideoDto;
import binding.mapper.VideoMapper;
import exception.VideoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.Video;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.VideoService;

import javax.validation.Valid;
import java.util.Set;

@Tag(name = "Video", description = "The video API")
@RestController
@RequestMapping("/api/video")
public class VideoController {

    private final VideoService videoService;

    private final VideoMapper videoMapper;

    public VideoController(VideoService videoService, VideoMapper videoMapper) {
        this.videoService = videoService;
        this.videoMapper = videoMapper;
    }

    @Operation(summary = "Create video", tags = "Video")
    @PostMapping("/")
    public ResponseEntity<VideoDto> add(@RequestBody @Valid VideoDto videoDto) {
        try {
            Video video = videoService.add(videoMapper.toModel(videoDto));
            return new ResponseEntity<VideoDto>(videoMapper.toDto(video), HttpStatus.OK);
        } catch (VideoException.VideoExists e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @Operation(summary = "Retrieve video by id", tags = "Video")
    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> getById(@PathVariable String id) {
        try {
            return new ResponseEntity<VideoDto>(videoMapper.toDto(videoService.getById(id)), HttpStatus.OK);
        } catch (VideoException.VideoNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Retrieve a list of videos by a title", tags = "Video")
    @GetMapping("/search/{filter}")
    public ResponseEntity<Set<VideoDto>> getByTitle(@PathVariable String filter) {
        return new ResponseEntity<Set<VideoDto>>(videoMapper.toDtoSet(videoService.getByTitle(filter)), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve a list of similar videos", tags = "Video")
    @PostMapping("/similar/number/{numberOfMatch}")
    public ResponseEntity<Set<VideoDto>> getSimilar(@RequestBody @Valid VideoDto videoDto,
                                                    @PathVariable int numberOfMatch) {
        return new ResponseEntity<Set<VideoDto>>(videoMapper.toDtoSet(videoService.getSimilar(videoMapper.toModel(videoDto), numberOfMatch)), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve all videos", tags = "Video")
    @GetMapping("/")
    public ResponseEntity<Set<VideoDto>> getAll() {
        return new ResponseEntity<Set<VideoDto>>(videoMapper.toDtoSet(videoService.getAll()), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve all deleted videos", tags = "Video")
    @GetMapping("/deleted")
    public ResponseEntity<Set<VideoDto>> getAllDeleted() {
        return new ResponseEntity<Set<VideoDto>>(videoMapper.toDtoSet(videoService.getAllDeleted()), HttpStatus.OK);
    }

    @Operation(summary = "Delete a video by id", tags = "Video")
    @DeleteMapping("/{id}")
    public ResponseEntity<VideoDto> delete(@PathVariable String id) throws Exception {
        return new ResponseEntity<VideoDto>(videoMapper.toDto(videoService.delete(id)), HttpStatus.OK);
    }
}
