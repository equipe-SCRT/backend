package school.sptech.backend.service.condominio.dto;

import lombok.Data;

@Data
public class QtdProdutosNaoConformeListagemDto {
    private Integer condominioId;
    private String nomeCondominio;
    private Integer qtdProdutos;
}
