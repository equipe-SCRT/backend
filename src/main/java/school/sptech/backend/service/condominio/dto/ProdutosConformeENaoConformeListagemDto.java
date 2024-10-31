package school.sptech.backend.service.condominio.dto;

import lombok.Data;

@Data
public class ProdutosConformeENaoConformeListagemDto {
    private Integer discrepancia;
    private String nomeCondominio;
    private String nomeProduto;
    private Integer qtdConforme;
    private Integer qtdNaoConforme;
}
