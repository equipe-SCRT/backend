package school.sptech.backend.api.metrica;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.domain.metrica.Metrica;
import school.sptech.backend.service.metrica.MetricaService;
import school.sptech.backend.service.metrica.dto.MetricaAtualizacaoDto;
import school.sptech.backend.service.metrica.dto.MetricaCriacaoDto;
import school.sptech.backend.service.metrica.dto.MetricaListagemDto;
import school.sptech.backend.service.metrica.dto.MetricaMapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

@RequestMapping("/metricas")
@RequiredArgsConstructor
@RestController
public class MetricaController {

    @Autowired
    private MetricaService metricas;

    @GetMapping
    public ResponseEntity<List<MetricaListagemDto>> getAllMetrica(){
        List<MetricaListagemDto> listar = metricas.listar();
        if (listar.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(listar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetricaListagemDto> getMetrica(@PathVariable int id){
        Metrica metrica = metricas.porId(id);
        List<MetricaListagemDto> dto = MetricaMapper.toDto(Collections.singletonList(metrica));

        return ResponseEntity.ok(dto.get(0));
    }

    @PostMapping
    public ResponseEntity<MetricaListagemDto> postMetrica(@RequestBody MetricaCriacaoDto metricaCriacaoDto) throws URISyntaxException {
        Metrica criar = metricas.criar(metricaCriacaoDto);
        URI uri = new URI("/metricas/"+criar.getId_metrica());
        List<MetricaListagemDto> dto = MetricaMapper.toDto(Collections.singletonList(criar));
        return ResponseEntity.created(uri).body(dto.get(0));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetricaListagemDto> putMetrica(@PathVariable int id, @RequestBody MetricaAtualizacaoDto metricaAtualizacaoDto){
        Metrica criar = metricas.atualizar(id, metricaAtualizacaoDto);
        List<MetricaListagemDto> dto = MetricaMapper.toDto(Collections.singletonList(criar));
        return ResponseEntity.ok(dto.get(0));
    }
}
