package school.sptech.backend.service.usuario.dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCriacaoDto {
    @NotNull
    @NotBlank
    @Size(min = 8)
    private String nome;
    @NotNull
    @Email
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    @Size(min = 8)
    private String senha;
    @NotNull
    @NotBlank
    @PositiveOrZero
    private Integer tipoUsuario;

}
