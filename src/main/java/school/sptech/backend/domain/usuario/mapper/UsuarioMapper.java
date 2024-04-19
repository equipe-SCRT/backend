package school.sptech.backend.domain.usuario.mapper;

import school.sptech.backend.domain.usuario.dto.UsuarioConsultaDto;
import school.sptech.backend.domain.usuario.dto.UsuarioCriacaoDto;
import school.sptech.backend.domain.usuario.entity.Usuario;

public class UsuarioMapper {
    public static UsuarioConsultaDto toDto(Usuario usuario){
        UsuarioConsultaDto usuarioConsultaDto = new UsuarioConsultaDto();
        usuarioConsultaDto.setTipoUsuario(usuario.getTipoUsuario());
        usuarioConsultaDto.setNome(usuario.getNome());
        usuarioConsultaDto.setEmail(usuario.getEmail());
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

}
