package school.sptech.backend.api.usuario;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.backend.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.backend.service.usuario.dto.UsuarioConsultaDtoJwt;
import school.sptech.backend.service.usuario.dto.UsuarioCriacaoDtoJwt;
import school.sptech.backend.service.usuario.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody @Valid UsuarioCriacaoDtoJwt usuarioCriacaoDto){
        this.usuarioService.criar(usuarioCriacaoDto);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto){
        System.out.println(usuarioLoginDto);
        UsuarioTokenDto usuarioToken = usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioToken);

    }

    @PostMapping("/recuperar-senha/{email}")
    public ResponseEntity<Void> recuperar(@PathVariable String email){
        Boolean emailEnviado = usuarioService.enviarEmail(email);
        return ResponseEntity.status(200).build();

    }

    @PatchMapping("/trocar-senha")
    public ResponseEntity<Void> alterarSenha(@RequestParam String code, @RequestParam String senha){
        Boolean emailEnviado = usuarioService.alterarSenha(code, senha);

        return ResponseEntity.status(200).build();
    }

//    @PostMapping
//    public ResponseEntity<UsuarioConsultaDto> usuarioConsultaDtoResponseEntity(@RequestBody UsuarioCriacaoDto usuarioCriacaoDto){
//        UsuarioConsultaDto user = usuarioService.adicionarUsuario(usuarioCriacaoDto);
//        return ResponseEntity.status(201).body(user);
//    }

//    @GetMapping
//    public ResponseEntity<List<UsuarioConsultaDtoJwt>> getGamer(){
//        List<UsuarioConsultaDtoJwt> usuarios = usuarioService.getUsuarios();
//        if (usuarios.isEmpty()){
//            return ResponseEntity.noContent().build();
//        }
//
//        return ResponseEntity.ok(usuarios);
//    }
}
