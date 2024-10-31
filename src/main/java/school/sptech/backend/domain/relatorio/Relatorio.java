package school.sptech.backend.domain.relatorio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioListagemDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relatorio {

    private String produto;
    private Integer qtdVencido;
    private Integer qtdArrecadado;

}
