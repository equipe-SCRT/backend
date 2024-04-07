package school.sptech.backendscrt.service.usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class UsuarioCriacaoDto {

    @Size(min = 3, max = 20)
    @Schema(description = "Nome do usuário", example = "Nome Sobrenome")
    private String nome;

    @Email
    @Schema(description = "Email do usuário", example = "nome.sobrenome@exemplo.com")
    private String email;

    @Size(min = 6)
    @Schema(description = "Senha do usuário", example = "1234")
    private String senha;

    @PositiveOrZero
    @Schema(description = "Tipo do usuário", example = "0")
    private int fkTipoUsuario;

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

    public int getFkTipoUsuario() {
        return fkTipoUsuario;
    }

    public void setFkTipoUsuario(int fkTipoUsuario) {
        this.fkTipoUsuario = fkTipoUsuario;
    }
}
