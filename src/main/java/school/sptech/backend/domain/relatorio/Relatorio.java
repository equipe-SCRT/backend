package school.sptech.backend.domain.relatorio;

import lombok.Data;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioListagemDto;

@Data
public class Relatorio {

    private ProdutoUnitarioListagemDto.Produto produto;
    private Integer qtdVencido;
    private Integer qtdArrecadado;
    private String mesReferencia;

    @Data
    public static class Produto {
        private Integer id;
        private String nome;
    }

}
