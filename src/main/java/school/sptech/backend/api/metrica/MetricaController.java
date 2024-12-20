package school.sptech.backend.api.metrica;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import school.sptech.backend.api.BaseController;
import school.sptech.backend.domain.historicomudanca.HistoricoMudanca;
import school.sptech.backend.domain.metrica.Metrica;

import school.sptech.backend.service.historicomudanca.dto.HistoricoMudancaListagemDto;

import school.sptech.backend.service.metrica.MetricaService;
import school.sptech.backend.service.metrica.dto.MetricaAtualizacaoDto;
import school.sptech.backend.service.metrica.dto.MetricaCriacaoDto;
import school.sptech.backend.service.metrica.dto.MetricaListagemDto;
import school.sptech.backend.service.metrica.dto.MetricaMapper;

import  school.sptech.backend.service.usuario.UsuarioService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/metricas")
public class MetricaController implements BaseController<MetricaCriacaoDto, MetricaAtualizacaoDto, MetricaListagemDto, Integer> {

    private final MetricaService service;
    private final MetricaMapper mapper;

    @PostMapping
    public ResponseEntity<MetricaListagemDto> criar(@RequestBody @Valid MetricaCriacaoDto metrica) {
        Metrica novaMetrica = mapper.toEntity(metrica);
        service.criar(novaMetrica);
        URI uri = URI.create("/metricas/" + novaMetrica.getId());
        return ResponseEntity.created(uri).body(mapper.toDto(novaMetrica));
    }

    @GetMapping
    public ResponseEntity<List<MetricaListagemDto>> listar() {
        List<Metrica> dtos = service.listar();
        List<MetricaListagemDto> listagemDtos = mapper.toDto(dtos);
        if(listagemDtos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listagemDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetricaListagemDto> porId(@PathVariable Integer id) {
        Metrica dto = service.porId(id);
        MetricaListagemDto consultaMetrica = mapper.toDto(dto);
        return ResponseEntity.ok().body(consultaMetrica);
    }
    @GetMapping("/ultimo")
    public ResponseEntity<MetricaListagemDto> ultimoAdicionado() {
        Metrica dto = service.ultimoAdicionado();
        MetricaListagemDto consultaMetrica = mapper.toDto(dto);
        return ResponseEntity.ok().body(consultaMetrica);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MetricaListagemDto> atualizar(@PathVariable Integer id, @RequestBody @Valid MetricaAtualizacaoDto metricaAtualizacao) {
        Metrica metrica = new Metrica();
        metrica.setId(id);
        Metrica resposta = service.atualizar(metrica.getId(), metrica);
        MetricaListagemDto listagemDto = mapper.toDto(resposta);

        return ResponseEntity.ok().body(listagemDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();

    }
}
