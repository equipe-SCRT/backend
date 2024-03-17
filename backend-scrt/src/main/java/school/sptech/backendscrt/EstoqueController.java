package school.sptech.backendscrt;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Tag(name = "Estoque")
@RequestMapping("/estoque")
public class EstoqueController {

    private Estoque estoque = new Estoque();
    
    @GetMapping
    public ResponseEntity<List<Produto>> getEstoqueProdutos(){
        if (!Objects.isNull(estoque.getProdutos())){
            return ResponseEntity.status(200).body(estoque.getProdutos());
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{indice}")
    public ResponseEntity<Produto> getProdutoPorId(@PathVariable int indice){
        if (isInList(indice)){
            return ResponseEntity.status(200).body(estoque.getProdutos().get(indice));
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping
    public ResponseEntity<Produto> postSetProduto(@RequestBody Produto produto){
        if (!Objects.isNull(produto)){
            estoque.addProdutos(produto);
            return ResponseEntity.status(201).body(produto);
        }
        return ResponseEntity.status(400).build();
    }
    
    @PutMapping("/indice")
    public ResponseEntity<Produto> postSetProduto(@RequestBody Produto produto, @PathVariable int indice){
        if (isInList(indice)){
            if (!Objects.isNull(produto)){
                estoque.getProdutos().set(indice, produto);
                return ResponseEntity.status(200).body(produto);
            }
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deleteProdutoEstoque(@PathVariable int indice){
        if (isInList(indice)){
            estoque.getProdutos().remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }
    
    private boolean isInList(int indice){
        return indice >= 0 && estoque.getProdutos().size() > indice;
    }
        
}
