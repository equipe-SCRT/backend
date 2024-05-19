package school.sptech.backend.service.origem.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrigemAtualizacaoDto {

    @NotNull
    @Positive
    private Integer auta_de_souza_rua;
    @NotNull
    @Positive
    private Integer itapora;
    @Positive
    private int fkCondominio;
    @Positive
    private int fkCampanha;
}
