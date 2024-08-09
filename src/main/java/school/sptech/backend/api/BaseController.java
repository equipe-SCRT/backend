package school.sptech.backend.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BaseController<ReqCriarDto, ReqAtualizarDto, ResDto, ID> {
    @PostMapping
    ResponseEntity<ResDto> criar(@Valid @RequestBody ReqCriarDto dto);

    @PutMapping("/{id}")
    ResponseEntity<ResDto> atualizar(@PathVariable ID id, @Valid @RequestBody ReqAtualizarDto dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletar(@PathVariable ID id);

    @GetMapping("/{id}")
    ResponseEntity<ResDto> porId(@PathVariable ID id);

    @GetMapping
    ResponseEntity<List<ResDto>> listar();
}
