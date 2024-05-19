package school.sptech.backend.service.origem.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class OrigemCriacaoDto   {

    @NotNull
    @Positive
    private Integer auta_de_souza_rua;
    @NotNull
    @Positive
    private Integer itapora;
    @Positive
    private int condominioId;
    @Positive
    private int campanhaId;

}
