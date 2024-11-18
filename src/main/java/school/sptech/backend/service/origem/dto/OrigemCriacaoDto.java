package school.sptech.backend.service.origem.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class OrigemCriacaoDto   {

    private Integer autaDeSouzaRua;

    private Integer itapora;

    private int condominioId;

    private int campanhaId;

}
