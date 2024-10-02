package school.sptech.backend.service.cesta.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.time.LocalDate;
@Data
public class CestaCriacaoDto {
    private String lote;
    @PastOrPresent
    private LocalDate dataMontagem;
    @NotNull
    @Positive
    private int qtdCestasMontadas;
    @NotNull
    private Integer tipoCestaId;
}
