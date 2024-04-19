package school.sptech.backend.api.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.backend.domain.usuario.dto.UsuarioConsultaDto;
import school.sptech.backend.domain.usuario.dto.UsuarioCriacaoDto;
import school.sptech.backend.domain.usuario.repository.UsuarioRepository;
import school.sptech.backend.service.usuario.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);
    @PostMapping
    public ResponseEntity<UsuarioConsultaDto> usuarioConsultaDtoResponseEntity(@RequestBody UsuarioCriacaoDto usuarioCriacaoDto){
        UsuarioConsultaDto user = usuarioService.adicionarUsuario(usuarioCriacaoDto);
        return ResponseEntity.status(201).body(user);
    }
}
