package school.sptech.backend.api.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.service.usuario.dto.UsuarioAtualizacaoDto;
import school.sptech.backend.service.usuario.dto.UsuarioConsultaDto;
import school.sptech.backend.service.usuario.dto.UsuarioCriacaoDto;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRep(@PathVariable int id){
        boolean deleted = usuarioService.deleteUsuariosById(id);

        if (deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/logar/{id}")
    public ResponseEntity<UsuarioConsultaDto> atualizar(@PathVariable int id,
                                                        @RequestBody UsuarioAtualizacaoDto usuarioAtualizacaoDto){
        UsuarioConsultaDto usuarioConsultaDto = usuarioService.atualizar(id, usuarioAtualizacaoDto);
        if (usuarioConsultaDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuarioConsultaDto);
    }

    @PatchMapping("/logar/{id}")
     public ResponseEntity<UsuarioConsultaDto> logar(@PathVariable int id){
        UsuarioConsultaDto usuarioConsultaDto = usuarioService.activeUsuariosById(id);
        if (usuarioConsultaDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuarioConsultaDto);
    }

    @PatchMapping("/deslogar/{id}")
    public ResponseEntity<UsuarioConsultaDto> deslogar(@PathVariable int id){
        UsuarioConsultaDto usuarioConsultaDto = usuarioService.deactiveUsuariosById(id);
        if (usuarioConsultaDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuarioConsultaDto);
    }
}
