package school.sptech.backendscrt.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.Model.TipoUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tipos-usuarios")
@Tag(name = "TipoUsuario")
public class TipoUsuarioController {

    List<TipoUsuario> tiposUsuarios = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<TipoUsuario>> consultar() {
        if (tiposUsuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(tiposUsuarios);
    }

    @PostMapping
    public ResponseEntity<TipoUsuario> adicionar(@RequestBody TipoUsuario tipoUsuario) {
        if (!Objects.isNull(tipoUsuario)) {
            tiposUsuarios.add(tipoUsuario);
            return ResponseEntity.status(201).body(tipoUsuario);
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{indice}")
    public ResponseEntity<TipoUsuario> atualizar(@RequestBody TipoUsuario tipoUsuario, @PathVariable int indice) {
        if (isInList(indice)) {
            tiposUsuarios.set(indice, tipoUsuario);
            return ResponseEntity.status(200).body(tipoUsuario);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice) {
        if (isInList(indice)) {
            tiposUsuarios.remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int indice){
        return indice >= 0 && indice < tiposUsuarios.size();
    }
}
