package school.sptech.backend.service.cesta.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;
@Data
public class CestaCriacaoDto {

    private Integer qtdCesta;
    @PastOrPresent
    private LocalDate dataMontagem;
    @NotNull
    private Integer tipoCestaId;
}
