package school.sptech.backendscrt.api.controller.usuario;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backendscrt.domain.usuario.Usuario;
import school.sptech.backendscrt.service.usuario.UsuarioService;
import school.sptech.backendscrt.service.usuario.dto.UsuarioCriacaoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    List<Usuario> usuarios = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Usuario>> consultar() {
        if (usuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarios);
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {
        this.usuarioService.criar(usuarioCriacaoDto);
        return ResponseEntity.status(201).build();

    }

    @PutMapping("/{indice}")
    public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario, @PathVariable int indice){
        if (isInList(indice)){
            usuarios.set(indice, usuario);
            return ResponseEntity.status(200).body(usuario);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice){
        if (isInList(indice)){
            usuarios.remove(indice);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    private boolean isInList(int indice){
        return indice >= 0 && indice < usuarios.size();
    }

}
