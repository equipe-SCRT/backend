package school.sptech.backend.service.origem.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrigemCriacaoDto   {

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
