package school.sptech.backendscrt.api.tipoProduto;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.domain.tipoProduto.TipoProduto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tipos-produtos")
@Tag(name = "TipoProduto")
public class TipoProdutoController {

    List<TipoProduto> tiposProdutos = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<TipoProduto>> consultar() {
        if (tiposProdutos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(tiposProdutos);
    }

    @PostMapping
    public ResponseEntity<TipoProduto> adicionar(@RequestBody TipoProduto tipoProduto) {
        if (!Objects.isNull(tipoProduto)) {
            tiposProdutos.add(tipoProduto);
            return ResponseEntity.status(201).body(tipoProduto);
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{indice}")
    public ResponseEntity<TipoProduto> atualizar(@RequestBody TipoProduto tipoProduto, @PathVariable int indice) {
        if (isInList(indice)) {
            tiposProdutos.set(indice, tipoProduto);
            return ResponseEntity.status(200).body(tipoProduto);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice) {
        if (isInList(indice)) {
            tiposProdutos.remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int indice){
        return indice >= 0 && indice < tiposProdutos.size();
    }
}
