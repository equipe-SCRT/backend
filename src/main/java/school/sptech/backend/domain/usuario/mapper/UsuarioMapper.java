package school.sptech.backend.domain.usuario.mapper;

import school.sptech.backend.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.backend.service.usuario.dto.UsuarioConsultaDto;
import school.sptech.backend.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.backend.domain.usuario.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto){
        Usuario usuario = new Usuario();

        usuario.setEmail(usuario.getEmail());
        usuario.setNome(usuario.getNome());
        usuario.setSenha(usuario.getSenha());
        usuario.setTipoUsuario(usuario.getTipoUsuario());

        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token){
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getIdUsuario());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return  usuarioTokenDto;
    }
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

    public static List<UsuarioConsultaDto> toDto(List<Usuario> usuarios){
        List<UsuarioConsultaDto> usuariosConsultaDto = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioConsultaDto usuarioConsultaDto = new UsuarioConsultaDto();
            usuarioConsultaDto.setTipoUsuario(usuario.getTipoUsuario());
            usuarioConsultaDto.setNome(usuario.getNome());
            usuarioConsultaDto.setEmail(usuario.getEmail());
            usuariosConsultaDto.add(usuarioConsultaDto);
        }
        return usuariosConsultaDto;
    }



}
