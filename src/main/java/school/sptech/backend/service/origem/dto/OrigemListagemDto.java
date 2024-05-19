package school.sptech.backend.service.origem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrigemListagemDto {

    private Integer id;
    private Integer auta_de_souza_rua;
    private Integer itapora;
    private int fkCondominio;
    private int fkCampanha;
}
