package school.sptech.backend.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.sptech.backend.service.usuario.dto.UsuarioConsultaDto;
import school.sptech.backend.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.backend.domain.usuario.entity.Usuario;
import school.sptech.backend.domain.usuario.mapper.UsuarioMapper;
import school.sptech.backend.domain.usuario.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioConsultaDto adicionarUsuario(UsuarioCriacaoDto usuarioCriacaoDto){
        Usuario usuario = UsuarioMapper.toEntity(usuarioCriacaoDto);

        usuarioRepository.save(usuario);

        UsuarioConsultaDto usuarioConsultaDto = UsuarioMapper.toDto(usuario);

        return usuarioConsultaDto;
    }

    public List<UsuarioConsultaDto> getUsuarios() {
        List<Usuario> all = usuarioRepository.findAll();

        List<UsuarioConsultaDto> lista = UsuarioMapper.toDto(all);

        return lista;
    }
}
