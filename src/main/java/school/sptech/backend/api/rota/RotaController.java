package school.sptech.backend.api.rota;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.api.BaseController;
import school.sptech.backend.domain.rota.Rota;
import school.sptech.backend.service.rota.RotaService;
import school.sptech.backend.service.rota.dto.RotaAtualizacaoDto;
import school.sptech.backend.service.rota.dto.RotaCriacaoDto;
import school.sptech.backend.service.rota.dto.RotaListagemDto;
import school.sptech.backend.service.rota.dto.RotaMapper;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rotas")
@RequiredArgsConstructor
public class RotaController implements BaseController<RotaCriacaoDto, RotaAtualizacaoDto, RotaListagemDto, Integer> {

    private final RotaService service;
    private final RotaMapper mapper;

    @PostMapping
    public ResponseEntity<RotaListagemDto> criar(@RequestBody @Valid RotaCriacaoDto dto) {
        Rota rotaCriada = this.service.criar(mapper.toEntity(dto));
        URI uri = URI.create("/rotas/" + rotaCriada.getId());
        return ResponseEntity.created(uri).body(mapper.toDto(rotaCriada));
    }

    @GetMapping
    public ResponseEntity<List<RotaListagemDto>> listar() {
        List<Rota> rotas = this.service.listar();
        return ResponseEntity.ok().body(mapper.toDto(rotas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RotaListagemDto> porId(@PathVariable Integer id) {
        Rota entity = this.service.porId(id);
        return ResponseEntity.ok().body(mapper.toDto(entity));
    }

    @GetMapping("/por-nome/{nome}")
    public ResponseEntity<RotaListagemDto> porNome(@PathVariable String nome) {
        Rota entity = this.service.porNome(nome);
        return ResponseEntity.ok().body(mapper.toDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RotaListagemDto> atualizar(@PathVariable Integer id, @Valid @RequestBody RotaAtualizacaoDto rotaAtualizada) {
        Rota entity = mapper.toEntity(rotaAtualizada);
        RotaListagemDto dto = mapper.toDto(this.service.atualizar(entity.getId(), entity));
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        this.service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
