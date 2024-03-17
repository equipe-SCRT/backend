package school.sptech.backendscrt;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

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

    List<Produto> produtos = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Produto>> consultar(){
        if (produtos.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(produtos);
    }

    @PostMapping
    public ResponseEntity<Produto> adicionar(@RequestBody Produto produto){
        if (!Objects.isNull(produto)){
            produtos.add(produto);
            return ResponseEntity.status(201).body(produto);
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{indice}")
    public ResponseEntity<Produto> atualizar(@RequestBody Produto produto, @PathVariable int indice){
        if (isInList(indice)){
            produtos.set(indice, produto);
            return ResponseEntity.status(200).body(produto);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice){
        if (isInList(indice)){
            produtos.remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }
    // verifica se está na lista
    private boolean isInList(int indice){
        return indice >= 0 && indice < produtos.size();
    }
}
