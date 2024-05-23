package school.sptech.backend.api.metrica;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import school.sptech.backend.domain.metrica.Metrica;

import school.sptech.backend.service.metrica.MetricaService;
import school.sptech.backend.service.metrica.dto.MetricaAtualizacaoDto;
import school.sptech.backend.service.metrica.dto.MetricaCriacaoDto;
import school.sptech.backend.service.metrica.dto.MetricaListagemDto;
import school.sptech.backend.service.metrica.dto.MetricaMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping("/metricas")
public class MetricaController {

    private final MetricaService service;
    private final MetricaMapper mapper;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid MetricaCriacaoDto metrica) {
        service.criar(mapper.toEntity(metrica));
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<MetricaListagemDto>> listar() {
        List<Metrica> dtos = service.listar();
        return ResponseEntity.ok(mapper.toDto(dtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetricaListagemDto> porId(@PathVariable int id) {
        Metrica dto = service.porId(id);
        return ResponseEntity.ok().body(mapper.toDto(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetricaListagemDto> atualizar(@RequestBody @Valid MetricaAtualizacaoDto metricaAtualizacao, @PathVariable int id) {
        Metrica dto = service.atualizar(metricaAtualizacao);
        return ResponseEntity.ok().body(mapper.toDto(dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
