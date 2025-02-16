package school.sptech.backend.domain.relatorio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioListagemDto;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relatorio {

    private String produto;
    private LocalDate dataValidade;
    private String origem;

}
