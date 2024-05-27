package school.sptech.backend.service.metrica.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MetricaCriacaoDto {

    @FutureOrPresent
    @NotNull
    private LocalDate alteracao;

    @Positive
    @NotNull
    private long fkUsuario;
}
