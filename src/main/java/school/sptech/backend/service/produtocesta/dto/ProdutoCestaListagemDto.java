package school.sptech.backend.service.produtocesta.dto;

import lombok.Data;

@Data
public class ProdutoCestaListagemDto {
    private Integer id;
    private String produto;
    private int qtdProduto;
}
