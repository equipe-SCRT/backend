package school.sptech.backend.service.metrica.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class MetricaAtualizacaoDto {

    @PositiveOrZero
    private Integer qtdCasas;
    @PositiveOrZero
    private Integer alertaVencimento;
}
