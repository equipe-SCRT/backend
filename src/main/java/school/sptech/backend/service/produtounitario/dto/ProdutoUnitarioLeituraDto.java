package school.sptech.backend.service.produtounitario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoUnitarioLeituraDto {
    private String produto;
    private String data;
    private Double peso;
    private String unidade;
    private String origem;
}
