package school.sptech.backend.service.campanha.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CampanhaListagemDto {

    private Integer id;
    private String localCampanha;
    private Date dataCampanha;
    private Integer qtdArrecadada;
    private Integer meta;
}
