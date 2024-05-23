package school.sptech.backend.service.historicomudanca.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class HistoricoMudancaAtualizacaoDto {

    @NotNull
    @NotBlank
    @Positive
    private int id_historico_mudanca;

    @NotNull
    @NotBlank
    private LocalDate data_hora;
    
    @NotNull
    @Positive
    private long fk_usuario;
    
}
