package school.sptech.backend.service.usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioCriacaoDto {
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


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
