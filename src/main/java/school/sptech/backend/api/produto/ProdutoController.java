package school.sptech.backend.api.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.service.produto.ProdutoService;
import school.sptech.backend.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.backend.service.produto.dto.ProdutoCriacaoDto;
import school.sptech.backend.service.produto.dto.ProdutoListagemDto;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody ProdutoCriacaoDto produtoCriacao){
        this.produtoService.criar(produtoCriacao);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<ProdutoListagemDto>> listar(){
        List<ProdutoListagemDto> dto = this.produtoService.listar();
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoListagemDto> porId(@PathVariable int id){
        ProdutoListagemDto dto = this.produtoService.porId(id);
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoListagemDto> atualizar(@RequestBody ProdutoAtualizacaoDto produtoAtualizado, @PathVariable int id){
        ProdutoListagemDto dto = this.produtoService.atualizar(produtoAtualizado, id);
        return ResponseEntity.status(200).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        this.produtoService.deletar(id);
        return ResponseEntity.status(204).build();
    }

}
