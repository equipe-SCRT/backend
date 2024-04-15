package school.sptech.backendscrt.api.produto;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.domain.produto.Produto;
import school.sptech.backendscrt.service.produto.ProdutoService;
import school.sptech.backendscrt.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.backendscrt.service.produto.dto.ProdutoCriacaoDto;
import school.sptech.backendscrt.service.produto.dto.ProdutoListagemDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController()
@Tag(name = "Produto")
@RequestMapping("/produtos")
/*
    Author: @edu-correa
    Meaning: Criar objetos Produtos por meio de endpoint, futuramente adicionar no banco
    Date: 06-03-2024
*/

public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // todo tofix - só está sendo usado pro metodo isInList
    List<ProdutoListagemDto> produtos = new ArrayList<>();

    // todo tofix - pra fazer o listar no service retornar void,
    // todo tofix - da pra colocar o status(200).body(produtos)
    @GetMapping
    public ResponseEntity<List<ProdutoListagemDto>> listar(){
        this.produtoService.listar();
        return ResponseEntity.status(200).build();
    }

    @PostMapping
    public ResponseEntity<Produto> adicionar(@RequestBody @Valid ProdutoCriacaoDto produtoCriacaoDto){
        if (Objects.isNull(produtoCriacaoDto)){
            return ResponseEntity.status(400).build();
        }
        this.produtoService.adicionar(produtoCriacaoDto);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoListagemDto> atualizar(@PathVariable int id, @RequestBody @Valid ProdutoAtualizacaoDto produtoAtualizacaoDto) {
        if (isInList(id)) {
            this.produtoService.atualizar(id, produtoAtualizacaoDto);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        if (isInList(id)){
            this.produtoService.deletar(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int id){
        return id > 0 && id < produtos.size();
    }
}
