package school.sptech.backend.api.condominio;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
public class CondominioController {

    private final CondominioService service;
    private final CondominioMapper mapper;

    @PostMapping
    public ResponseEntity<CondominioListagemDto> criar(@RequestBody @Valid CondominioCriacaoDto dto) {
        Condominio condominioCriado = mapper.toEntity(dto);
        Condominio resposta = this.service.criar(condominioCriado, dto.getEnderecoId());
        URI uri = URI.create("/condominios/" + resposta.getId());
        return ResponseEntity.created(uri).body(mapper.toDto(resposta));
    }

    @GetMapping
    public ResponseEntity<List<CondominioListagemDto>> listar() {
        List<Condominio> condominios = this.service.listar();
        return ResponseEntity.ok().body(mapper.toDto(condominios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondominioListagemDto> porId(@PathVariable int id) {
        Condominio entity = this.service.porId(id);
        return ResponseEntity.ok().body(mapper.toDto(entity));
    }

    @GetMapping("/por-nome/{nome}")
    public ResponseEntity<CondominioListagemDto> porNome(@PathVariable String nome) {
        Condominio entity = this.service.porNome(nome);
        return ResponseEntity.ok().body(mapper.toDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CondominioListagemDto> atualizar(@RequestBody @Valid CondominioAtualizacaoDto condominioAtualizado, @PathVariable int id) {
        Condominio entity = mapper.atualizacaoDto(condominioAtualizado, id);
        CondominioListagemDto dto = mapper.toDto(this.service.atualizar(entity, entity.getId()));
        return ResponseEntity.ok().body(dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        this.service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
