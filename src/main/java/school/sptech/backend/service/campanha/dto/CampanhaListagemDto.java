package school.sptech.backend.service.campanha.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CampanhaListagemDto {

    private Integer id;
    private String nome;
    private String local_campanha;
    private Date data_campanha;
    private Integer qtd_arrecadada;
    private Integer meta;
}
