package school.sptech.backendscrt.api.controller.tipoProduto;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.domain.tipoProduto.TipoProduto;
import school.sptech.backendscrt.service.tipoproduto.TipoProdutoService;
import school.sptech.backendscrt.service.tipoproduto.dto.TipoProdutoAtualizacaoDto;
import school.sptech.backendscrt.service.tipoproduto.dto.TipoProdutoCriacaoDto;
import school.sptech.backendscrt.service.tipoproduto.dto.TipoProdutoListagemDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tipos-produtos")
@Tag(name = "TipoProduto")
public class TipoProdutoController {

    @Autowired
    private TipoProdutoService tipoProdutoService;

    @PostMapping
    public ResponseEntity<Void> adicionar(@RequestBody @Valid TipoProdutoCriacaoDto tipoProduto) {
        this.tipoProdutoService.criar(tipoProduto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<TipoProdutoListagemDto>> consultar() {
        List<TipoProdutoListagemDto> dto = this.tipoProdutoService.listar();
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoProdutoListagemDto> consultar(@PathVariable int id) {
        TipoProdutoListagemDto dto = this.tipoProdutoService.porId(id);
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoProdutoListagemDto> atualizar(@RequestBody @Valid TipoProdutoAtualizacaoDto tipoProdutoAtualizado, @PathVariable int id) {
        TipoProdutoListagemDto dto = this.tipoProdutoService.atualizar(tipoProdutoAtualizado, id);
        return ResponseEntity.status(200).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        this.tipoProdutoService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
