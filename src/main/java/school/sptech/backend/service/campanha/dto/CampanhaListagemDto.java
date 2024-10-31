package school.sptech.backend.service.campanha.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class CampanhaListagemDto {

    private Integer id;
    private String nome;
    private String localCampanha;
    private LocalDate dataCampanha;
    private Integer qtdArrecadada;
    private Integer meta;
}
