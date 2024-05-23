package school.sptech.backend.service.usuario.dto;


import jakarta.validation.constraints.*;

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
