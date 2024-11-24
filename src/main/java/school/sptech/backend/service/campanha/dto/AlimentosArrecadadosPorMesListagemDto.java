package school.sptech.backend.service.campanha.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AlimentosArrecadadosPorMesListagemDto {
    private Integer qtdArrecadada;
    private LocalDate dataCampanha;
}
