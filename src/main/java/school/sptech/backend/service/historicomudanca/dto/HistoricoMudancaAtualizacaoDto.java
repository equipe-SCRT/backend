package school.sptech.backend.service.historicomudanca.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class HistoricoMudancaAtualizacaoDto {


    @NotNull
    private LocalDate dataHora;

    @Positive
    private Long fkUsuario;
    
}
