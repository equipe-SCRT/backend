package school.sptech.backend.service.usuario.autenticacao.dto;

import lombok.Data;

@Data
public class UsuarioTokenDto {

    private Integer userId;
    private String nome;
    private String email;
    private String token;
    private Integer tipoUsuario;

}
