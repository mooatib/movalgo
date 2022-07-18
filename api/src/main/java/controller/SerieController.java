package controller;

import binding.dto.SerieDto;
import binding.mapper.SerieMapper;
import exception.SerieException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.Serie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.SerieService;

import java.util.Set;

@Tag(name = "Serie", description = "The serie API")
@RestController
@RequestMapping("/api/serie")
public class SerieController {

    private final SerieService serieService;

    private final SerieMapper serieMapper;

    public SerieController(SerieService serieService, SerieMapper serieMapper) {
        this.serieService = serieService;
        this.serieMapper = serieMapper;
    }

    @Operation(summary = "Create serie", tags = "Serie")
    @PostMapping("/")
    public ResponseEntity<SerieDto> add(@RequestBody SerieDto serieDto) {
        try {
            Serie serie = serieService.add(serieMapper.toModel(serieDto));
            return new ResponseEntity<SerieDto>(serieMapper.toDto(serie), HttpStatus.OK);
        } catch (SerieException.SerieExists e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @Operation(summary = "Retrieve all series", tags = "Serie")
    @GetMapping("/")
    public ResponseEntity<Set<SerieDto>> getAll() {
        return new ResponseEntity<Set<SerieDto>>(serieMapper.toDtoSet(serieService.getAll()), HttpStatus.OK);
    }
}
