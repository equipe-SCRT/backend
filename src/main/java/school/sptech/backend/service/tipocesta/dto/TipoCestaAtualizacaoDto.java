package school.sptech.backend.service.tipocesta.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TipoCestaAtualizacaoDto {
    @NotBlank
    private String nome;

}
