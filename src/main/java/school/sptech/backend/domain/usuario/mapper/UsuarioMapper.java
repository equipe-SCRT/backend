package school.sptech.backend.domain.usuario.mapper;

import school.sptech.backend.service.usuario.dto.UsuarioAtualizacaoDto;
import school.sptech.backend.service.usuario.dto.UsuarioConsultaDto;
import school.sptech.backend.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.backend.domain.usuario.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {
    public static UsuarioConsultaDto toDto(Usuario usuario){
        UsuarioConsultaDto usuarioConsultaDto = new UsuarioConsultaDto();
        usuarioConsultaDto.setTipoUsuario(usuario.getTipoUsuario());
        usuarioConsultaDto.setNome(usuario.getNome());
        usuarioConsultaDto.setEmail(usuario.getEmail());
        usuarioConsultaDto.setLogado(usuario.getLogado());
        return usuarioConsultaDto;
    }
    public static Usuario toEntity(UsuarioCriacaoDto usuarioCriacaoDto){
        Usuario usuario = new Usuario();
        usuario.setTipoUsuario(usuarioCriacaoDto.getTipoUsuario());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setSenha(usuario.getSenha());
        return usuario;
    }

    public static List<UsuarioConsultaDto> toDto(List<Usuario> usuarios){
        List<UsuarioConsultaDto> usuariosConsultaDto = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioConsultaDto usuarioConsultaDto = new UsuarioConsultaDto();
            usuarioConsultaDto.setTipoUsuario(usuario.getTipoUsuario());
            usuarioConsultaDto.setNome(usuario.getNome());
            usuarioConsultaDto.setEmail(usuario.getEmail());
            usuarioConsultaDto.setLogado(usuario.getLogado());
            usuariosConsultaDto.add(usuarioConsultaDto);
        }
        return usuariosConsultaDto;
    }

    public static Usuario toEntity(UsuarioAtualizacaoDto usuarioCriacaoDto){
        Usuario usuario = new Usuario();
        usuario.setTipoUsuario(usuarioCriacaoDto.getTipoUsuario());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setSenha(usuario.getSenha());
        return usuario;
    }


}
