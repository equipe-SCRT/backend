package school.sptech.backend.api.metrica;

import java.net.URI;
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
    public ResponseEntity<MetricaListagemDto> cadastrar(@RequestBody @Valid MetricaCriacaoDto metrica) {
        Metrica novaMetrica = mapper.toEntity(metrica);
        service.criar(novaMetrica);
        URI uri = URI.create("/metricas/" + novaMetrica.getIdMetrica());
        return ResponseEntity.created(uri).body(mapper.toDto(novaMetrica));
    }

    @GetMapping
    public ResponseEntity<List<MetricaListagemDto>> listar() {
        List<Metrica> dtos = service.listar();
        List<MetricaListagemDto> listagemDtos = mapper.toDto(dtos);
        return ResponseEntity.ok(listagemDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetricaListagemDto> porId(@PathVariable int id) {
        Metrica dto = service.porId(id);
        MetricaListagemDto consultaMetrica = mapper.toDto(dto);
        return ResponseEntity.ok().body(consultaMetrica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetricaListagemDto> atualizar(@RequestBody @Valid MetricaAtualizacaoDto metricaAtualizacao, @PathVariable int id) {

        Metrica dto = service.atualizar(metricaAtualizacao, id);
        MetricaListagemDto listagemDto = mapper.toDto(dto);

        return ResponseEntity.ok().body(listagemDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
