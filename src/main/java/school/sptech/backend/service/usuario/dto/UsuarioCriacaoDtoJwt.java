package school.sptech.backend.service.usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCriacaoDtoJwt {
    @NotNull
    @NotBlank
    @Schema(description = "Nome do usuário", example = "Fulvia Cristina")
    private String nome;
    @NotNull
    @Email
    @Schema(description = "Email do usuário", example = "fulvia@itapora.com")
    private String email;
    @NotNull
    @Schema(description = "Senha do usuário", example = "LoveItapora")
    private String senha;
    @NotNull
    @Schema(description = "Se o usuário é admin", example = "1")
    private Integer tipoUsuario;



}
