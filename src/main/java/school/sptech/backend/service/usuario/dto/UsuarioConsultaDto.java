package school.sptech.backend.service.usuario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioConsultaDto {
    private Integer id;
    private String nome;
    private String email;

    private Integer tipoUsuario;
    private Boolean logado;


}
