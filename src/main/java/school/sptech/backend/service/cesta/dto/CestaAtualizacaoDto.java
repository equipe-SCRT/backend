package school.sptech.backend.service.cesta.dto;

import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CestaAtualizacaoDto {
    private String lote;
    private LocalDate dataMontagem;
    private Long tipoCestaId;
}
