package school.sptech.backend.service.metrica.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MetricaCriacaoDto {
    

    private LocalDate alteracao;
    private long fkUsuario;
}
