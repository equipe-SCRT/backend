package school.sptech.backend.api.condominio;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.service.condominio.CondominioService;
import school.sptech.backend.service.condominio.dto.CondominioAtualizacaoDto;
import school.sptech.backend.service.condominio.dto.CondominioCriacaoDto;
import school.sptech.backend.service.condominio.dto.CondominioListagemDto;

import java.util.List;

@RestController
@RequestMapping("/condominios")
@RequiredArgsConstructor
public class CondominioController {

    @Autowired
    private final CondominioService service;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid CondominioCriacaoDto dto) {
        this.service.criar(dto);
        return ResponseEntity.created(null).build();
    }

    @GetMapping
    public ResponseEntity<List<CondominioListagemDto>> listar() {
        List<CondominioListagemDto> dtos = this.service.listar();
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondominioListagemDto> porId(@PathVariable int id) {
        CondominioListagemDto dto = this.service.porId(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/por-nome/{nome}")
    public ResponseEntity<CondominioListagemDto> porNome(@PathVariable String nome) {
        CondominioListagemDto dto = this.service.porNome(nome);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CondominioListagemDto> atualizar(@RequestBody @Valid CondominioAtualizacaoDto condominioAtualizado, @PathVariable int id) {
        CondominioListagemDto dto = this.service.atualizar(condominioAtualizado, id);
        return ResponseEntity.ok().body(dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        this.service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
