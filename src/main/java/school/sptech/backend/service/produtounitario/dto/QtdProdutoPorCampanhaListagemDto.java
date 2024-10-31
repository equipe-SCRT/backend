package school.sptech.backend.service.produtounitario.dto;

import lombok.Data;

@Data
public class QtdProdutoPorCampanhaListagemDto {
    private Integer produtoId;
    private String nome;
    private Integer qtdProdutos;
}
