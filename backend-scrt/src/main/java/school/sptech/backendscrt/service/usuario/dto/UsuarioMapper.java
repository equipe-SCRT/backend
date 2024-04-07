package school.sptech.backendscrt.service.usuario.dto;

import school.sptech.backendscrt.domain.usuario.Usuario;

public class UsuarioMapper {
    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto){
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setFkTipoUsuario(usuarioCriacaoDto.getFkTipoUsuario());

        return usuario;
    }
}
