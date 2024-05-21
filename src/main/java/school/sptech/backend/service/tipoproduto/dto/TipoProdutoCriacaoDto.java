package school.sptech.backend.service.tipoproduto.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoProdutoCriacaoDto {
    @Size(min = 3, max = 20)
    private String nome;
}
