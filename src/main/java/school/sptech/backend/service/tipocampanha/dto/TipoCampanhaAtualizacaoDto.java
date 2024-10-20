package school.sptech.backend.service.tipocampanha.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoCampanhaAtualizacaoDto {


    @NotBlank
    @Size(min = 2, max = 40)
    private String nome;
}
