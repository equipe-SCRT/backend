package school.sptech.backend.service.produtounitario.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class QtdVencidoPorMesDto {
    private Integer qtd;
    private LocalDate dataValidade;
    private Integer produtoId;
}
