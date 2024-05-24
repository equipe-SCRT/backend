package school.sptech.backend.service.unidademedida.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UnidadeMedidaAtualizacaoDto {
    @NotBlank
    private String nome;

    @NotBlank
    private String representacao;
}
