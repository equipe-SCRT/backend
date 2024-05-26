package school.sptech.backend.api.metrica;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
public class MetricaController {

    private final MetricaService service;
    private final MetricaMapper mapper;
    private  final UsuarioService userService;

    @PostMapping
    public ResponseEntity<MetricaListagemDto> criar(@RequestBody @Valid MetricaCriacaoDto metrica) {
        System.out.println(metrica);
        Metrica novaMetrica = toEntidade(metrica);
        System.out.println(novaMetrica);
        service.criar(novaMetrica);
        System.out.println(novaMetrica);
        URI uri = URI.create("/metricas/" + novaMetrica.getIdMetrica());
        return ResponseEntity.created(uri).body(toListagem(novaMetrica));
    }

    @GetMapping
    public ResponseEntity<List<MetricaListagemDto>> listar() {
        List<Metrica> dtos = service.listar();
        List<MetricaListagemDto> listagemDtos = toListagem(dtos);
        return ResponseEntity.ok(listagemDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetricaListagemDto> porId(@PathVariable int id) {
        Metrica dto = service.porId(id);
        MetricaListagemDto consultaMetrica = toListagem(dto);
        return ResponseEntity.ok().body(consultaMetrica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetricaListagemDto> atualizar(@RequestBody @Valid MetricaAtualizacaoDto metricaAtualizacao, @PathVariable int id) {
        Metrica metrica = new Metrica();
        metrica.setIdMetrica(id);
        metrica.setAlteracao(metricaAtualizacao.getAlteracao());
        metrica.setUsuario(userService.porId(metricaAtualizacao.getFkUsuario()));
        Metrica resposta = service.atualizar(metrica);
        MetricaListagemDto listagemDto = toListagem(resposta);

        return ResponseEntity.ok().body(listagemDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    public MetricaListagemDto toListagem(Metrica entity){
        MetricaListagemDto listagemDto = new MetricaListagemDto();
        listagemDto.setAlteracao(entity.getAlteracao());
        MetricaListagemDto.Usuario usuario = new MetricaListagemDto.Usuario();

        usuario.setNome(entity.getUsuario().getNome());
        usuario.setEmail(entity.getUsuario().getEmail());
        usuario.setTipoUsuario(entity.getUsuario().getTipoUsuario());

        listagemDto.setUsuario(usuario);
        return listagemDto;
    }

    public List<MetricaListagemDto> toListagem(List<Metrica> entity){
       return entity.stream().map(this::toListagem).collect(Collectors.toList());
    }

    public Metrica toEntidade(MetricaCriacaoDto dto){
       Metrica metrica = new Metrica();
       metrica.setAlteracao(dto.getAlteracao());
       metrica.setUsuario(userService.porId(dto.getFkUsuario()));
       return metrica;
    }
}
