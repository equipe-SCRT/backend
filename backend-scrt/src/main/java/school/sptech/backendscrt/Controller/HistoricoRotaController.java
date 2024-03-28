package school.sptech.backendscrt.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.Model.HistoricoRota;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/historicos-rotas")
@Tag(name = "HistoricoRota")
public class HistoricoRotaController {

    List<HistoricoRota> historicosRotas = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<HistoricoRota>> consultar() {
        if (historicosRotas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(historicosRotas);
    }

    @PostMapping
    public ResponseEntity<HistoricoRota> adicionar(@RequestBody HistoricoRota historicoRota) {
        if (!Objects.isNull(historicoRota)) {
            historicosRotas.add(historicoRota);
            return ResponseEntity.status(201).body(historicoRota);
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{indice}")
    public ResponseEntity<HistoricoRota> atualizar(@RequestBody HistoricoRota historicoRota, @PathVariable int indice) {
        if (isInList(indice)) {
            historicosRotas.set(indice, historicoRota);
            return ResponseEntity.status(200).body(historicoRota);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice) {
        if (isInList(indice)) {
            historicosRotas.remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int indice){
        return indice >= 0 && indice < historicosRotas.size();
    }
}
