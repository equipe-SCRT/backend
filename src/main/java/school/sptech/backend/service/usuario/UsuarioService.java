package school.sptech.backend.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.usuario.dto.UsuarioConsultaDto;
import school.sptech.backend.domain.usuario.dto.UsuarioCriacaoDto;
import school.sptech.backend.domain.usuario.entity.Usuario;
import school.sptech.backend.domain.usuario.mapper.UsuarioMapper;
import school.sptech.backend.domain.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {
    UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioConsultaDto adicionarUsuario(UsuarioCriacaoDto usuarioCriacaoDto){
        Usuario usuario = UsuarioMapper.toEntity(usuarioCriacaoDto);

        usuarioRepository.save(usuario);

        UsuarioConsultaDto usuarioConsultaDto = UsuarioMapper.toDto(usuario);

        return usuarioConsultaDto;
    }
}
