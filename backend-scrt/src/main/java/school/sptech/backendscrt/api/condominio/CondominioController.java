package school.sptech.backendscrt.api.condominio;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.domain.condominio.Condominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/condominios")
@Tag(name =  "Condominio")
public class CondominioController {

    List<Condominio> condominios = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Condominio>> consultar() {
        if (condominios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(condominios);
    }

    @PostMapping
    public ResponseEntity<Condominio> adicionar(@RequestBody Condominio condominio) {
        if (!Objects.isNull(condominio)) {
            condominios.add(condominio);
            return ResponseEntity.status(201).body(condominio);
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{indice}")
    public ResponseEntity<Condominio> atualizar(@RequestBody Condominio condominio, @PathVariable int indice) {
        if (isInList(indice)) {
            condominios.set(indice, condominio);
            return ResponseEntity.status(200).body(condominio);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice) {
        if (isInList(indice)) {
            condominios.remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int indice){
        return indice >= 0 && indice < condominios.size();
    }
}
