package school.sptech.backend.api.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.service.usuario.dto.UsuarioConsultaDto;
import school.sptech.backend.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.backend.domain.usuario.repository.UsuarioRepository;
import school.sptech.backend.service.usuario.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<UsuarioConsultaDto> usuarioConsultaDtoResponseEntity(@RequestBody UsuarioCriacaoDto usuarioCriacaoDto){
        UsuarioConsultaDto user = usuarioService.adicionarUsuario(usuarioCriacaoDto);
        return ResponseEntity.status(201).body(user);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioConsultaDto>> getGamer(){
        List<UsuarioConsultaDto> usuarios = usuarioService.getUsuarios();
        if (usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }
}
