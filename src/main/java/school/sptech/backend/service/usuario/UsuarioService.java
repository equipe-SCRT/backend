package school.sptech.backend.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.backend.service.usuario.dto.UsuarioAtualizacaoDto;
import school.sptech.backend.service.usuario.dto.UsuarioConsultaDto;
import school.sptech.backend.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.backend.domain.usuario.entity.Usuario;
import school.sptech.backend.domain.usuario.mapper.UsuarioMapper;
import school.sptech.backend.domain.usuario.repository.UsuarioRepository;
import school.sptech.backend.service.usuario.dto.UsuarioLoginConsultaDto;

import java.util.List;
import java.util.Optional;

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

    public UsuarioConsultaDto activeUsuariosById(Integer id) {
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isEmpty()) return null;
        Usuario usuario = user.get();
        usuario.setLogado(true);
        Usuario saved = usuarioRepository.save(usuario);
        UsuarioConsultaDto lista = UsuarioMapper.toDto(saved);

        return lista;
    }

    public UsuarioConsultaDto atualizar(Integer id, UsuarioAtualizacaoDto usuarioAtualizacaoDto) {
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isEmpty()) return null;
        Usuario novoUsuario = UsuarioMapper.toEntity(usuarioAtualizacaoDto);
        novoUsuario.setIdUsuario(id);
        Usuario saved = usuarioRepository.save(novoUsuario);
        UsuarioConsultaDto lista = UsuarioMapper.toDto(saved);

        return lista;
    }

    public UsuarioConsultaDto deactiveUsuariosById(Integer id) {
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isEmpty()) return null;
        Usuario usuario = user.get();
        usuario.setLogado(false);
        Usuario saved = usuarioRepository.save(usuario);
        UsuarioConsultaDto lista = UsuarioMapper.toDto(saved);

        return lista;
    }


    public boolean deleteUsuariosById(Integer id) {
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isEmpty()) return false;

        usuarioRepository.deleteById(id);

        return true;
    }

    public UsuarioConsultaDto existeUsuario(UsuarioLoginConsultaDto usuarioLoginConsultaDto) {
        String email  = usuarioLoginConsultaDto.getEmail();
        String senha = usuarioLoginConsultaDto.getSenha();
        List<Usuario> user = usuarioRepository.findByEmailEqualsIgnoreCaseAndSenhaEquals(
                email, senha);

        if (user.isEmpty()) return null;

        Usuario usuario = user.get(0);
        usuario.setLogado(true);

        usuarioRepository.save(usuario);

        UsuarioConsultaDto dto = UsuarioMapper.toDto(usuario);

        return dto;

    }
}
