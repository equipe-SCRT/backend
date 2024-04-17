package school.sptech.backendscrt.service.unidademedida.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UnidadeMedidaAtualizacao {
    @NotBlank
    @Size(min = 3, max = 20)
    private String nome;
    @NotBlank
    @Size(min = 1, max = 2)
    private String representacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRepresentacao() {
        return representacao;
    }

    public void setRepresentacao(String representacao) {
        this.representacao = representacao;
    }
}
