package school.sptech.backendscrt.service.produtounitario.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ProdutoUnitarioCriacaoDto {
    @NotBlank
    @Size(min = 5, max = 100)
    // todo tofix -
    @Schema(description = "Nome do produto unitário", example = "Arroz")
    private String nome;
    @NotBlank
    @Future
    @Schema(description = "Data de validade do produto", example = "2024-08-09")
    private LocalDate dataValidade;
    @NotNull
    @Schema(description = "Peso do produto", example = "5")
    private double peso;
    @NotNull
    @PositiveOrZero
    @Schema(description = "Origem do produto", example = "0")
    private int fkOrigem;
    @NotNull
    @PositiveOrZero
    @Schema(description = "Unidade de medida do produto", example = "0")
    private int fkUnidadeMedida;
    @NotNull
    @PositiveOrZero
    @Schema(description = "Cesta que o produto pertence", example = "0")
    private int fkCesta;
    @NotNull
    @PositiveOrZero
    @Schema(description = "Produto que o produto unitário se refere", example = "0")
    private int fkProduto;
    @NotNull
    @PositiveOrZero
    @Schema(description = "Rota que o produto pertence", example = "0")
    private int fkRota;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getFkOrigem() {
        return fkOrigem;
    }

    public void setFkOrigem(int fkOrigem) {
        this.fkOrigem = fkOrigem;
    }

    public int getFkUnidadeMedida() {
        return fkUnidadeMedida;
    }

    public void setFkUnidadeMedida(int fkUnidadeMedida) {
        this.fkUnidadeMedida = fkUnidadeMedida;
    }

    public int getFkCesta() {
        return fkCesta;
    }

    public void setFkCesta(int fkCesta) {
        this.fkCesta = fkCesta;
    }

    public int getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(int fkProduto) {
        this.fkProduto = fkProduto;
    }

    public int getFkRota() {
        return fkRota;
    }

    public void setFkRota(int fkRota) {
        this.fkRota = fkRota;
    }
}
