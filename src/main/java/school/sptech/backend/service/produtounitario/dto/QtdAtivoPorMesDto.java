package school.sptech.backend.service.produtounitario.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class QtdAtivoPorMesDto {
    private Integer qtd;
    private LocalDate criadoEm;
    private Integer produtoId;

}
