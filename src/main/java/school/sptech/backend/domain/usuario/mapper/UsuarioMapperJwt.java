package school.sptech.backend.domain.usuario.mapper;

import school.sptech.backend.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.backend.service.usuario.dto.UsuarioConsultaDtoJwt;
import school.sptech.backend.service.usuario.dto.UsuarioCriacaoDtoJwt;
import school.sptech.backend.domain.usuario.entity.UsuarioJwt;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapperJwt {

    public static UsuarioJwt of(UsuarioCriacaoDtoJwt usuarioCriacaoDto){
        UsuarioJwt usuario = new UsuarioJwt();

        usuario.setEmail(usuario.getEmail());
        usuario.setNome(usuario.getNome());
        usuario.setSenha(usuario.getSenha());
        usuario.setTipoUsuario(usuario.getTipoUsuario());

        return usuario;
    }

    public static UsuarioTokenDto of(UsuarioJwt usuario, String token){
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getIdUsuario());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return  usuarioTokenDto;
    }
    public static UsuarioConsultaDtoJwt toDto(UsuarioJwt usuario){
        UsuarioConsultaDtoJwt usuarioConsultaDto = new UsuarioConsultaDtoJwt();
        usuarioConsultaDto.setTipoUsuario(usuario.getTipoUsuario());
        usuarioConsultaDto.setNome(usuario.getNome());
        usuarioConsultaDto.setEmail(usuario.getEmail());
        return usuarioConsultaDto;
    }
    public static UsuarioJwt toEntity(UsuarioCriacaoDtoJwt usuarioCriacaoDto){
        UsuarioJwt usuario = new UsuarioJwt();
        usuario.setTipoUsuario(usuarioCriacaoDto.getTipoUsuario());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setSenha(usuario.getSenha());
        return usuario;
    }

    public static List<UsuarioConsultaDtoJwt> toDto(List<UsuarioJwt> usuarios){
        List<UsuarioConsultaDtoJwt> usuariosConsultaDto = new ArrayList<>();

        for (UsuarioJwt usuario : usuarios) {
            UsuarioConsultaDtoJwt usuarioConsultaDto = new UsuarioConsultaDtoJwt();
            usuarioConsultaDto.setTipoUsuario(usuario.getTipoUsuario());
            usuarioConsultaDto.setNome(usuario.getNome());
            usuarioConsultaDto.setEmail(usuario.getEmail());
            usuariosConsultaDto.add(usuarioConsultaDto);
        }
        return usuariosConsultaDto;
    }



}
