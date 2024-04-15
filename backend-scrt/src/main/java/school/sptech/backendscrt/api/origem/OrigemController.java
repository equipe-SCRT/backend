package school.sptech.backendscrt.api.origem;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.domain.origem.Origem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/origens")
@Tag(name = "Origem")
public class OrigemController {

    List<Origem> origens = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Origem>> consultar() {
        if (origens.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(origens);
    }

    @PostMapping
    public ResponseEntity<Origem> adicionar(@RequestBody Origem origem) {
        if (!Objects.isNull(origem)) {
            origens.add(origem);
            return ResponseEntity.status(201).body(origem);
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{indice}")
    public ResponseEntity<Origem> atualizar(@RequestBody Origem origem, @PathVariable int indice) {
        if (isInList(indice)) {
            origens.set(indice, origem);
            return ResponseEntity.status(200).body(origem);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice) {
        if (isInList(indice)) {
            origens.remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int indice){
        return indice >= 0 && indice < origens.size();
    }
}
