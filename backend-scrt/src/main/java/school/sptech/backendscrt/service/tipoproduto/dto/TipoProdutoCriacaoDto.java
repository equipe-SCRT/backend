package school.sptech.backendscrt.service.tipoproduto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TipoProdutoCriacaoDto {
    @NotBlank
    @Size(min = 3, max = 20)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
