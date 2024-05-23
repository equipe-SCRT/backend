package school.sptech.backend.service.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoCriacaoDto {
    @NotBlank
    @Size(min = 3, max = 15)
    private String nome;
    @NotNull
    @Positive
    private int tipoProdutoId;
}
