package school.sptech.backendscrt.service.produto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class ProdutoCriacaoDto {
    @NotBlank
    @Size(min = 5, max = 45)
    @Schema(description = "Nome do produto", example = "Arroz")
    private String nome;
    @NotNull
    @PositiveOrZero
    @Schema(description = "Tipo do produto", example = "0")
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
