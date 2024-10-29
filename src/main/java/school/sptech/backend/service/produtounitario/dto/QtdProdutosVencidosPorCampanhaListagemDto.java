package school.sptech.backend.service.produtounitario.dto;

import lombok.Data;

@Data
public class QtdProdutosVencidosPorCampanhaListagemDto {
    private Integer campanhaId;
    private String nome;
    private Integer produtoId;
    private Integer qtdProdutosVencidos;
}
