package school.sptech.backendscrt.service.campanha.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class CampanhaCriacaoDto {
    @NotBlank
    @Size(min = 5, max = 100)
    @Schema(description = "Nome da Campanha", example = "Escola Viva")
    private String nome;
    @NotBlank
    @Size(min = 5, max = 100)
    @Schema(description = "Local da Campanha", example = "Escola Viva")
    private String local;
    @FutureOrPresent
    @NotBlank
    @Schema(description = "Data da campanha", example = "2024-05-21")
    private LocalDate dataCampanha;
    @NotNull
    @PositiveOrZero
    @Schema(description = "Quantidade de produtos arrecadada da campanha", example = "70")
    private int qtdArrecadada;
    @NotNull
    @Positive
    @Schema(description = "Meta de arrecadação da campanha", example = "80")
    private int meta;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDate getDataCampanha() {
        return dataCampanha;
    }

    public void setDataCampanha(LocalDate dataCampanha) {
        this.dataCampanha = dataCampanha;
    }

    public int getQtdArrecadada() {
        return qtdArrecadada;
    }

    public void setQtdArrecadada(int qtdArrecadada) {
        this.qtdArrecadada = qtdArrecadada;
    }

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }
}
