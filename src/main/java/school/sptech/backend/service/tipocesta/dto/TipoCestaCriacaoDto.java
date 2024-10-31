package school.sptech.backend.service.tipocesta.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TipoCestaCriacaoDto {
    @NotBlank
    private String nome;
}
