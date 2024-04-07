package school.sptech.backendscrt.api.controller.rua;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.domain.endereco.Endereco;
import school.sptech.backendscrt.domain.rua.Rua;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/ruas")
@Tag(name = "Rua")
public class RuaController {

    List<Rua> ruas = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Rua>> consultar() {
        if (ruas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(ruas);
    }

    @PostMapping
    public ResponseEntity<Rua> adicionar(@RequestBody Rua rua) {
        if (!Objects.isNull(rua)) {
            ruas.add(rua);
            return ResponseEntity.status(201).body(rua);
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("/{indice}")
    public ResponseEntity<Rua> adicionarEndereco(@RequestBody Endereco endereco, @PathVariable int indice) {
        if (isInList(indice)) {
            ruas.get(indice).adicionarEndereco(endereco);
            return ResponseEntity.status(201).body(ruas.get(indice));
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{indice}")
    public ResponseEntity<Rua> atualizar(@RequestBody Rua rua, @PathVariable int indice) {
        if (isInList(indice)) {
            ruas.set(indice, rua);
            return ResponseEntity.status(200).body(rua);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice) {
        if (isInList(indice)) {
            ruas.remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int indice){
        return indice >= 0 && indice < ruas.size();
    }
}
