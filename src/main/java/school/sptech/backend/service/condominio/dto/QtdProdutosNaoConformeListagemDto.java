package school.sptech.backend.service.condominio.dto;

import lombok.Data;

@Data
public class QtdProdutosNaoConformeListagemDto {
    private String nomeCondominio;
    private String nomeProduto;
    private Integer qtdProdutos;
}
