package school.sptech.backendscrt.lote.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.cesta.model.Cesta;
import school.sptech.backendscrt.lote.model.Lote;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/lotes")
@Tag(name = "Lote")
public class LoteController {

    List<Lote> lotes = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Lote>> consultar() {
        if (lotes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(lotes);
    }

    @PostMapping
    public ResponseEntity<Lote> adicionar(@RequestBody Lote lote) {
        if (!Objects.isNull(lote)) {
            lotes.add(lote);
            return ResponseEntity.status(201).body(lote);
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("/{indice}")
    public ResponseEntity<Lote> adicionarCesta(@RequestBody Cesta cesta, @PathVariable int indice) {
        if (isInList(indice)) {
            lotes.get(indice).adicionarCesta(cesta);
            return ResponseEntity.status(201).body(lotes.get(indice));
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{indice}")
    public ResponseEntity<Lote> atualizar(@RequestBody Lote lote, @PathVariable int indice) {
        if (isInList(indice)) {
            lotes.set(indice, lote);
            return ResponseEntity.status(200).body(lote);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice) {
        if (isInList(indice)) {
            lotes.remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int indice){
        return indice >= 0 && indice < lotes.size();
    }
}
