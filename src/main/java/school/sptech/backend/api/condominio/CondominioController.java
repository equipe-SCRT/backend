package school.sptech.backend.api.condominio;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.api.BaseController;
import school.sptech.backend.domain.condominio.Condominio;
import school.sptech.backend.service.condominio.CondominioService;
import school.sptech.backend.service.condominio.dto.CondominioAtualizacaoDto;
import school.sptech.backend.service.condominio.dto.CondominioCriacaoDto;
import school.sptech.backend.service.condominio.dto.CondominioListagemDto;
import school.sptech.backend.service.condominio.dto.CondominioMapper;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/condominios")
@RequiredArgsConstructor
public class CondominioController implements BaseController<CondominioCriacaoDto,CondominioAtualizacaoDto,CondominioListagemDto,Integer> {

    private final CondominioService service;
    private final CondominioMapper mapper;

    @PostMapping
    public ResponseEntity<CondominioListagemDto> criar(@RequestBody @Valid CondominioCriacaoDto dto) {
        Condominio condominioCriado = mapper.toEntity(dto);
        Condominio resposta = this.service.criar(condominioCriado);
        URI uri = URI.create("/condominios/" + resposta.getId());
        return ResponseEntity.created(uri).body(mapper.toDto(resposta));
    }

    @GetMapping
    public ResponseEntity<List<CondominioListagemDto>> listar() {
        List<Condominio> condominios = this.service.listar();
        return ResponseEntity.ok().body(mapper.toDto(condominios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondominioListagemDto> porId(@PathVariable Integer id) {
        Condominio entity = this.service.porId(id);
        return ResponseEntity.ok().body(mapper.toDto(entity));
    }

    @GetMapping("/por-nome/{nome}")
    public ResponseEntity<CondominioListagemDto> porNome(@PathVariable String nome) {
        Condominio entity = this.service.porNome(nome);
        return ResponseEntity.ok().body(mapper.toDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CondominioListagemDto> atualizar(@PathVariable Integer id, @RequestBody @Valid CondominioAtualizacaoDto condominioAtualizado) {
        Condominio entity = mapper.toEntity(condominioAtualizado);
        CondominioListagemDto dto = mapper.toDto(this.service.atualizar(entity.getId(), entity));
        return ResponseEntity.ok().body(dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        this.service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
