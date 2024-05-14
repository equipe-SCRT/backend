package school.sptech.backend.service.produtocesta.dto;

import lombok.Data;

@Data
public class ProdutoCestaEntityDto {
    private Integer id;
    private String nome;
    private int fkTipoProduto;
    private int idCesta;
}
