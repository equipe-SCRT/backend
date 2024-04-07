package school.sptech.backendscrt.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.backendscrt.domain.usuario.Usuario;
import school.sptech.backendscrt.domain.usuario.repository.UsuarioRepository;
import school.sptech.backendscrt.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.backendscrt.service.usuario.dto.UsuarioMapper;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void criar(UsuarioCriacaoDto usuarioCriacaoDto){
        final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);
        this.usuarioRepository.save(novoUsuario);
    }
}
