package school.sptech.backend.api.controller.usuario;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.backend.service.usuario.autenticacao.dto.UsuarioTokenDto;
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
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto){
        this.usuarioService.criar(usuarioCriacaoDto);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto){
        UsuarioTokenDto usuarioToken = this.usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200) . body(usuarioToken);

    }

//    @PostMapping
//    public ResponseEntity<UsuarioConsultaDto> usuarioConsultaDtoResponseEntity(@RequestBody UsuarioCriacaoDto usuarioCriacaoDto){
//        UsuarioConsultaDto user = usuarioService.adicionarUsuario(usuarioCriacaoDto);
//        return ResponseEntity.status(201).body(user);
//    }

    @GetMapping
    public ResponseEntity<List<UsuarioConsultaDto>> getGamer(){
        List<UsuarioConsultaDto> usuarios = usuarioService.getUsuarios();
        if (usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }
}
