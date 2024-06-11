package school.sptech.backend.service.produtounitario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProdutoUnitarioCountMesDto {

    private Object mes;
    private Long count;

}