package school.sptech.backendscrt.api.controller.unidademedida;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.service.unidademedida.UnidadeMedidaService;
import school.sptech.backendscrt.service.unidademedida.dto.UnidadeMedidaAtualizacao;
import school.sptech.backendscrt.service.unidademedida.dto.UnidadeMedidaCriacaoDto;
import school.sptech.backendscrt.service.unidademedida.dto.UnidadeMedidaListagemDto;

import java.util.List;

@RestController
@RequestMapping("/unidade-medidas")
@Tag(name = "UnidadeMedida")
public class UnidadeMedidaController {

    @Autowired
    UnidadeMedidaService unidadeMedidaService;

    @PostMapping
    public ResponseEntity<Void> adicionar(@RequestBody @Valid UnidadeMedidaCriacaoDto unidadeMedida){
        this.unidadeMedidaService.criar(unidadeMedida);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<UnidadeMedidaListagemDto>> listar(){
        List<UnidadeMedidaListagemDto> dto = this.unidadeMedidaService.listar();
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeMedidaListagemDto> porId(@PathVariable int id){
        UnidadeMedidaListagemDto dto = this.unidadeMedidaService.porId(id);
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeMedidaListagemDto> atualizar(@RequestBody UnidadeMedidaAtualizacao unidadeMedidaAtualizado, @PathVariable int id){
        UnidadeMedidaListagemDto dto = this.unidadeMedidaService.atualizar(unidadeMedidaAtualizado, id);
        return ResponseEntity.status(200).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        this.unidadeMedidaService.deletar(id);
        return ResponseEntity.status(200).build();
    }

}
