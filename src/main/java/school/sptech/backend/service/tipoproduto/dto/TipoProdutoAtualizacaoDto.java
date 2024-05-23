package school.sptech.backend.service.tipoproduto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoProdutoAtualizacaoDto {
    @NotBlank
    @Size(min = 3, max = 20)
    private String nome;
}
