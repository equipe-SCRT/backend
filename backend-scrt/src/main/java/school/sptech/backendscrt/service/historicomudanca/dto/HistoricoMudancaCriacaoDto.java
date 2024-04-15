package school.sptech.backendscrt.service.historicomudanca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public class HistoricoMudancaCriacaoDto {
    @FutureOrPresent
    @NotBlank
    @Schema(description = "Data e hora que a mudança foi realizada", example = "2024-05-21")
    private LocalDate dataHora;
    @NotNull
    @PositiveOrZero
    @Schema(description = "Usuário que realizou a mudança", example = "0")
    private int fkUsuario;

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
