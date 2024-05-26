package school.sptech.backend.service.historicomudanca.dto;

import java.time.LocalDate;

import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class HistoricoMudancaCriacaoDto {

    @FutureOrPresent
    private LocalDate dataHora;

    @Positive
    private Long fkUsuario;

}
