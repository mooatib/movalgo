package controller;

import binding.dto.VideoDto;
import binding.mapper.VideoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.VideoService;

import java.util.Set;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    private final VideoService videoService;

    private final VideoMapper videoMapper;

    public VideoController(VideoService videoService, VideoMapper videoMapper) {
        this.videoService = videoService;
        this.videoMapper = videoMapper;
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> add(@RequestBody VideoDto videoDto) {
        videoService.add(videoMapper.toModel(videoDto));
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> getById(@PathVariable String id) throws Exception {
        return new ResponseEntity<VideoDto>(videoMapper.toDto(videoService.getById(id)), HttpStatus.OK);
    }

    @GetMapping("/search/{filter}")
    public ResponseEntity<Set<VideoDto>> getByTitle(@PathVariable String filter) {
        return new ResponseEntity<Set<VideoDto>>(videoMapper.toDtoSet(videoService.getByTitle(filter)), HttpStatus.OK);
    }

    @PostMapping("/similar/number/{numberOfMatch}")
    public ResponseEntity<Set<VideoDto>> getSimilar(@RequestBody VideoDto videoDto,
                                                    @PathVariable int numberOfMatch) {
        return new ResponseEntity<Set<VideoDto>>(videoMapper.toDtoSet(videoService.getSimilar(videoMapper.toModel(videoDto), numberOfMatch)), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Set<VideoDto>> getAll() {
        return new ResponseEntity<Set<VideoDto>>(videoMapper.toDtoSet(videoService.getAll()), HttpStatus.OK);
    }

    @GetMapping("/deleted")
    public ResponseEntity<Set<VideoDto>> getAllDeleted() {
        return new ResponseEntity<Set<VideoDto>>(videoMapper.toDtoSet(videoService.getAllDeleted()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) throws Exception {
        videoService.delete(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
