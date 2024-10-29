package school.sptech.backend.service.condominio.dto;

import lombok.Data;

@Data
public class ProdutosArrecadadosPorCondominioListagemDto {

    private String nomeCondominio;
    private String mes;
    private Integer count;
}
