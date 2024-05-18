package school.sptech.backend.api.rota;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.service.rota.RotaService;
import school.sptech.backend.service.rota.dto.RotaAtualizacaoDto;
import school.sptech.backend.service.rota.dto.RotaCriacaoDto;
import school.sptech.backend.service.rota.dto.RotaListagemDto;

import java.util.List;

@RestController
@RequestMapping("/rotas")
@RequiredArgsConstructor
public class RotaController {

    @Autowired
    private RotaService service;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid RotaCriacaoDto dto) {
        this.service.criar(dto);
        return ResponseEntity.created(null).build();
    }

    @GetMapping
    public ResponseEntity<List<RotaListagemDto>> listar() {
        List<RotaListagemDto> dtos = this.service.listar();
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RotaListagemDto> porId(@PathVariable int id) {
        RotaListagemDto dto = this.service.porId(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/por-nome/{nome}")
    public ResponseEntity<RotaListagemDto> porNome(@PathVariable String nome) {
        RotaListagemDto dto = this.service.porNome(nome);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RotaListagemDto> atualizar(@RequestBody @Valid RotaAtualizacaoDto rotaAtualizada, @PathVariable int id) {
        RotaListagemDto dto = this.service.atualizar(rotaAtualizada, id);
        return ResponseEntity.ok().body(dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        this.service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
