package school.sptech.backend.service.produto.dto;

import lombok.Data;
import org.mapstruct.Builder;

@Data
public class ProdutoListagemDto {
    private Integer id;
    private String nome;
    private int qtdUnidadeMedida;
    private TipoProduto tipoProduto;
    private UnidadeMedida unidadeMedida;

    @Data
    public static class TipoProduto {
        private Integer id;
        private String nome;
    }

    @Data
    public static class UnidadeMedida {
        private Integer id;
        private String nome;
        private String representacao;
    }
}
