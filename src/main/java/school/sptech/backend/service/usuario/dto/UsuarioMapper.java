package school.sptech.backend.service.usuario.dto;

import school.sptech.backend.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.backend.service.usuario.dto.UsuarioConsultaDtoJwt;
import school.sptech.backend.service.usuario.dto.UsuarioCriacaoDtoJwt;
import school.sptech.backend.domain.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    public static Usuario of(UsuarioCriacaoDtoJwt usuarioCriacaoDto){
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setTipoUsuario(usuarioCriacaoDto.getTipoUsuario());

        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token){
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());

        if (usuario.getTipoUsuario() == 1){
            usuarioTokenDto.setTipoUsuario("Administrador do Sistema");
        }else if (usuario.getTipoUsuario() == 0){
            usuarioTokenDto.setTipoUsuario("usuário comum");
        }

        usuarioTokenDto.setToken(token);

        return  usuarioTokenDto;
    }
    public static UsuarioConsultaDtoJwt toDto(Usuario usuario){
        UsuarioConsultaDtoJwt usuarioConsultaDto = new UsuarioConsultaDtoJwt();
        usuarioConsultaDto.setTipoUsuario(usuario.getTipoUsuario());
        usuarioConsultaDto.setNome(usuario.getNome());
        usuarioConsultaDto.setEmail(usuario.getEmail());
        return usuarioConsultaDto;
    }
    public static Usuario toEntity(UsuarioCriacaoDtoJwt usuarioCriacaoDto){
        Usuario usuario = new Usuario();
        usuario.setTipoUsuario(usuarioCriacaoDto.getTipoUsuario());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setSenha(usuario.getSenha());
        return usuario;
    }

    public static List<UsuarioConsultaDtoJwt> toDto(List<Usuario> usuarios){
        List<UsuarioConsultaDtoJwt> usuariosConsultaDto = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioConsultaDtoJwt usuarioConsultaDto = new UsuarioConsultaDtoJwt();
            usuarioConsultaDto.setId(usuario.getId());
            usuarioConsultaDto.setTipoUsuario(usuario.getTipoUsuario());
            usuarioConsultaDto.setNome(usuario.getNome());
            usuarioConsultaDto.setEmail(usuario.getEmail());
            usuariosConsultaDto.add(usuarioConsultaDto);
        }
        return usuariosConsultaDto;
    }



}
