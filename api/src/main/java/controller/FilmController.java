package controller;

import binding.dto.FilmDto;
import binding.mapper.FilmMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.FilmService;

import java.util.Set;

@RestController
@RequestMapping("/api/film")
public class FilmController {

    private final FilmService filmService;

    private final FilmMapper filmMapper;

    public FilmController(FilmService filmService, FilmMapper filmMapper) {
        this.filmService = filmService;
        this.filmMapper = filmMapper;
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> add(@RequestBody FilmDto filmDto) {
        filmService.add(filmMapper.toModel(filmDto));
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Set<FilmDto>> getAll() {
        return new ResponseEntity<Set<FilmDto>>(filmMapper.toDtoSet(filmService.getAll()), HttpStatus.OK);
    }
}
