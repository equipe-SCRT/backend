package school.sptech.backend.service.metrica.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MetricaCriacaoDto {

    @PositiveOrZero
    private Integer qtdCasas;

    @PositiveOrZero
    private Integer alertaVencimento;
}
