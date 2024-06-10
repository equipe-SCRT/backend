package school.sptech.backend.service.usuario.autenticacao.dto;

import lombok.Data;

@Data
public class UsuarioLoginDto {

    private String email;
    private String senha;

}
