package school.sptech.backend.service.historicomudanca.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class HistoricoMudancaCriacaoDto {

    @NotNull
    private LocalDate dataHora;

    @NotNull
    @NotBlank
    @Positive
    private long fkUsuario;
    
}
