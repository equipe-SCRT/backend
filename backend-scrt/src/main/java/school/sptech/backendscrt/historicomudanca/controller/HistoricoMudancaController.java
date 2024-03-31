package school.sptech.backendscrt.historicomudanca.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.historicomudanca.model.HistoricoMudanca;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/historicos-mudancas")
@Tag(name = "HistoricoMudanca")
public class HistoricoMudancaController {

    List<HistoricoMudanca> historicosMudancas = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<HistoricoMudanca>> consultar() {
        if (historicosMudancas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(historicosMudancas);
    }

    @PostMapping
    public ResponseEntity<HistoricoMudanca> adicionar(@RequestBody HistoricoMudanca historicoMudanca) {
        if (!Objects.isNull(historicoMudanca)) {
            historicosMudancas.add(historicoMudanca);
            return ResponseEntity.status(201).body(historicoMudanca);
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{indice}")
    public ResponseEntity<HistoricoMudanca> atualizar(@RequestBody HistoricoMudanca historicoMudanca, @PathVariable int indice) {
        if (isInList(indice)) {
            historicosMudancas.set(indice, historicoMudanca);
            return ResponseEntity.status(200).body(historicoMudanca);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice) {
        if (isInList(indice)) {
            historicosMudancas.remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int indice){
        return indice >= 0 && indice < historicosMudancas.size();
    }
}
