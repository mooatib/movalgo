package controller;

import binding.dto.FilmDto;
import binding.mapper.FilmMapper;
import exception.FilmException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.Film;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.FilmService;

import java.util.Set;

@Tag(name = "Film", description = "The film API")
@RestController
@RequestMapping("/api/film")
public class FilmController {

    private final FilmService filmService;

    private final FilmMapper filmMapper;

    public FilmController(FilmService filmService, FilmMapper filmMapper) {
        this.filmService = filmService;
        this.filmMapper = filmMapper;
    }

    @Operation(summary = "Create film", tags = "Film")
    @PostMapping("/")
    public ResponseEntity<FilmDto> add(@RequestBody FilmDto filmDto) {
        try {
            Film film = filmService.add(filmMapper.toModel(filmDto));
            return new ResponseEntity<FilmDto>(filmMapper.toDto(film), HttpStatus.OK);
        } catch (FilmException.FilmExists e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @Operation(summary = "Retrieve all films", tags = "Film")
    @GetMapping("/")
    public ResponseEntity<Set<FilmDto>> getAll() {
        return new ResponseEntity<Set<FilmDto>>(filmMapper.toDtoSet(filmService.getAll()), HttpStatus.OK);
    }
}
