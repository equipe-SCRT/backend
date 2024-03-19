package school.sptech.backendscrt.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.Model.Produto;
import school.sptech.backendscrt.Model.TipoCesta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tipos-cestas")
@Tag(name = "TipoCesta")
public class TipoCestaController {

    List<TipoCesta> tiposCestas = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<TipoCesta>> consultar() {
        if (tiposCestas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(tiposCestas);
    }

    @PostMapping
    public ResponseEntity<TipoCesta> adicionar(@RequestBody TipoCesta tipoCesta) {
        if (!Objects.isNull(tipoCesta)) {
            tiposCestas.add(tipoCesta);
            return ResponseEntity.status(201).body(tipoCesta);
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("/{indice}")
    public ResponseEntity<TipoCesta> adicionarProduto(@RequestBody Produto produto, @PathVariable int indice) {
        if (isInList(indice)) {
            tiposCestas.get(indice).adicionarProduto(produto);
            return ResponseEntity.status(201).body(tiposCestas.get(indice));
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{indice}")
    public ResponseEntity<TipoCesta> atualizar(@RequestBody TipoCesta tipoCesta, @PathVariable int indice) {
        if (isInList(indice)) {
            tiposCestas.set(indice, tipoCesta);
            return ResponseEntity.status(200).body(tipoCesta);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice) {
        if (isInList(indice)) {
            tiposCestas.remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int indice){
        return indice >= 0 && indice < tiposCestas.size();
    }
}
