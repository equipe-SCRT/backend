package school.sptech.backend.service.produtocesta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoCestaCriacaoDto {

    private int idTipoCesta;
    private Produto produto;
    private String nome;

    @Getter
    @Setter
    public class Produto{
        private int idProduto;
        private int qtdProduto;
    }
    private int qtdProduto;
    private int produtoId;
    private int tipoCestaId;
}
