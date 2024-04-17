package school.sptech.backendscrt.api.controller.tipoCesta;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.domain.produto.Produto;
import school.sptech.backendscrt.domain.tipoCesta.TipoCesta;
import school.sptech.backendscrt.service.tipocesta.TipoCestaService;
import school.sptech.backendscrt.service.tipocesta.dto.TipoCestaAtualizacaoDto;
import school.sptech.backendscrt.service.tipocesta.dto.TipoCestaCriacaoDto;
import school.sptech.backendscrt.service.tipocesta.dto.TipoCestaListagemDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/tipos-cestas")
@Tag(name = "TipoCesta")
public class TipoCestaController {

    @Autowired
    private TipoCestaService tipoCestaService;

    @PostMapping
    public ResponseEntity<Void> adicionar(@RequestBody TipoCestaCriacaoDto tipoCesta) {
        this.tipoCestaService.criar(tipoCesta);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<TipoCestaListagemDto>> consultar() {
        List<TipoCestaListagemDto> dto = this.tipoCestaService.listar();
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCestaListagemDto> porId(@PathVariable int id) {
        TipoCestaListagemDto dto = this.tipoCestaService.porId(id);
        return ResponseEntity.status(200).body(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TipoCestaListagemDto> atualizar(@RequestBody @Valid TipoCestaAtualizacaoDto tipoCestaAtualizada, @PathVariable int id) {
        TipoCestaListagemDto dto = this.tipoCestaService.atualizar(tipoCestaAtualizada, id);
        return ResponseEntity.status(200).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        this.tipoCestaService.deletar(id);
        return ResponseEntity.status(204).build();
    }

}
