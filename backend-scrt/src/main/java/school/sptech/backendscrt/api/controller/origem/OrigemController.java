package school.sptech.backendscrt.api.controller.origem;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.service.origem.OrigemService;
import school.sptech.backendscrt.service.origem.dto.OrigemAtualizacaoDto;
import school.sptech.backendscrt.service.origem.dto.OrigemCriacaoDto;
import school.sptech.backendscrt.service.origem.dto.OrigemListagemDto;

import java.util.List;

@RestController
@RequestMapping("/origens")
@Tag(name = "Origem")
public class OrigemController {

    @Autowired
    private OrigemService origemService;

    @PostMapping
    public ResponseEntity<Void> adicionar(@RequestBody @Valid OrigemCriacaoDto origem) {
        this.origemService.criar(origem);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<OrigemListagemDto>> consultar() {
        List<OrigemListagemDto> origens = this.origemService.listar();
        return ResponseEntity.status(200).body(origens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrigemListagemDto> porId(@PathVariable int id){
        OrigemListagemDto origem = this.origemService.porId(id);
        return ResponseEntity.status(200).body(origem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrigemListagemDto> atualizar(@RequestBody @Valid OrigemAtualizacaoDto origemAtualizado, @PathVariable int id) {
        OrigemListagemDto origem = this.origemService.atualizar(origemAtualizado, id);
        return ResponseEntity.status(200).body(origem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        this.origemService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
