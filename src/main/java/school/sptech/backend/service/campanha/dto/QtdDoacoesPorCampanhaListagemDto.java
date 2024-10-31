package school.sptech.backend.service.campanha.dto;

import lombok.Data;

@Data
public class QtdDoacoesPorCampanhaListagemDto {
    private Integer qtdArrecadada;
    private String nome;
    private Integer mes;
    private Integer ano;
}
