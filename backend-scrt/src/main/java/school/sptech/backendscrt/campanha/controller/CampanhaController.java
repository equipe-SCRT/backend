package school.sptech.backendscrt.campanha.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.campanha.model.Campanha;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/campanhas")
@Tag(name = "Campanha")
public class CampanhaController {

    List<Campanha> campanhas = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Campanha>> consultar() {
        if (campanhas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(campanhas);
    }

    @PostMapping
    public ResponseEntity<Campanha> adicionar(@RequestBody Campanha campanha) {
        if (!Objects.isNull(campanha)) {
            campanhas.add(campanha);
            return ResponseEntity.status(201).body(campanha);
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{indice}")
    public ResponseEntity<Campanha> atualizar(@RequestBody Campanha campanha, @PathVariable int indice) {
        if (isInList(indice)) {
            campanhas.set(indice, campanha);
            return ResponseEntity.status(200).body(campanha);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice) {
        if (isInList(indice)) {
            campanhas.remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int indice){
        return indice >= 0 && indice < campanhas.size();
    }
}
