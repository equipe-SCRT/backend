package school.sptech.backendscrt;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    List<Endereco> enderecos = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Endereco>> consultar() {
        if (enderecos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(enderecos);
    }

    @PostMapping
    public ResponseEntity<Endereco> adicionar(@RequestBody Endereco endereco) {
        if (!Objects.isNull(endereco)) {
            enderecos.add(endereco);
            return ResponseEntity.status(201).body(endereco);
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{indice}")
    public ResponseEntity<Endereco> atualizar(@RequestBody Endereco endereco, @PathVariable int indice) {
        if (isInList(indice)) {
            enderecos.set(indice, endereco);
            return ResponseEntity.status(200).body(endereco);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice) {
        if (isInList(indice)) {
            enderecos.remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int indice){
        return indice >= 0 && indice < enderecos.size();
    }
}
