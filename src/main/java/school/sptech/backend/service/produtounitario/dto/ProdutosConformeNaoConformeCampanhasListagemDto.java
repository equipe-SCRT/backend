package school.sptech.backend.service.produtounitario.dto;

import lombok.Data;

@Data
public class ProdutosConformeNaoConformeCampanhasListagemDto {
    private String nome;
    private Integer conforme;
    private Integer naoConforme;
}
