package controller;

import binding.dto.SerieDto;
import binding.mapper.SerieMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.SerieService;

import java.util.Set;

@RestController
@RequestMapping("/api/serie")
public class SerieController {

    private final SerieService serieService;

    private final SerieMapper serieMapper;

    public SerieController(SerieService serieService, SerieMapper serieMapper) {
        this.serieService = serieService;
        this.serieMapper = serieMapper;
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> add(@RequestBody SerieDto serieDto) {
        serieService.add(serieMapper.toModel(serieDto));
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Set<SerieDto>> getAll() {
        return new ResponseEntity<Set<SerieDto>>(serieMapper.toDtoSet(serieService.getAll()), HttpStatus.OK);
    }
}
