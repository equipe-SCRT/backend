package school.sptech.backend.service.cesta.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class CestaCriacaoDto {
    private String loteId;
    private LocalDate dataMontagem;
    private String tipoCestaId;
}
