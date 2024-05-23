package school.sptech.backend.service.produto.dto;

import lombok.Data;
import org.mapstruct.Builder;

@Data
public class ProdutoListagemDto {
    private Integer id;
    private String nome;
    private TipoProduto tipoProduto;

    @Data
    public static class TipoProduto {
        private Integer id;
        private String nome;
    }
}
