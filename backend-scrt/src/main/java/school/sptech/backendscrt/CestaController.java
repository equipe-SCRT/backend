package school.sptech.backendscrt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cestas")
public class CestaController {

    List<Cesta> cestas = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Cesta>> consultar() {
        if (cestas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(cestas);
    }

    @PostMapping
    public ResponseEntity<Cesta> adicionar(@RequestBody Cesta cesta) {
        if (!Objects.isNull(cesta)) {
            cestas.add(cesta);
            return ResponseEntity.status(201).body(cesta);
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("/{indice}")
    public ResponseEntity<Cesta> adicionarProduto(@RequestBody Produto produto, @PathVariable int indice) {
        if (isInList(indice)) {
            cestas.get(indice).adicionarProduto(produto);
            return ResponseEntity.status(201).body(cestas.get(indice));
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{indice}")
    public ResponseEntity<Cesta> atualizar(@RequestBody Cesta cesta, @PathVariable int indice) {
        if (isInList(indice)) {
            cestas.set(indice, cesta);
            return ResponseEntity.status(200).body(cesta);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice) {
        if (isInList(indice)) {
            cestas.remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int indice){
        return indice >= 0 && indice < cestas.size();
    }
}
