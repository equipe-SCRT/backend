package school.sptech.backendscrt.rota.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.rota.model.Rota;
import school.sptech.backendscrt.rua.model.Rua;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/rotas")
@Tag(name = "Rota")
public class RotaController {

    List<Rota> rotas = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Rota>> consultar() {
        if (rotas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(rotas);
    }

    @PostMapping
    public ResponseEntity<Rota> adicionar(@RequestBody Rota rota) {
        if (!Objects.isNull(rota)) {
            rotas.add(rota);
            return ResponseEntity.status(201).body(rota);
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("/{indice}")
    public ResponseEntity<Rota> adicionarRua(@RequestBody Rua rua, @PathVariable int indice) {
        if (isInList(indice)) {
            rotas.get(indice).adicionarRua(rua);
            return ResponseEntity.status(201).body(rotas.get(indice));
        }
        return ResponseEntity.status(400).build();
    }


    @PutMapping("/{indice}")
    public ResponseEntity<Rota> atualizar(@RequestBody Rota rota, @PathVariable int indice) {
        if (isInList(indice)) {
            rotas.set(indice, rota);
            return ResponseEntity.status(200).body(rota);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice) {
        if (isInList(indice)) {
            rotas.remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int indice){
        return indice >= 0 && indice < rotas.size();
    }
}
