package school.sptech.backend.service.campanha.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class QtdDoacoesPorCampanhaListagemDto {
    private Integer qtdArrecadada;
    private String nome;
    private LocalDate dataCampanha;
}
