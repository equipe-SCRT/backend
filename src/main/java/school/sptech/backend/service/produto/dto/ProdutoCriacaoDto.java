package school.sptech.backend.service.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProdutoCriacaoDto {
    @NotBlank
    @Size(min = 3, max = 15)
    private String nome;
    @NotNull
    @Positive
    private int fkTipoProduto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFkTipoProduto() {
        return fkTipoProduto;
    }

    public void setFkTipoProduto(int fkTipoProduto) {
        this.fkTipoProduto = fkTipoProduto;
    }
}
