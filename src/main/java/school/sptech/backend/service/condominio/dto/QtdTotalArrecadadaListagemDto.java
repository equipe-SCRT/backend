package school.sptech.backend.service.condominio.dto;

import lombok.Data;

@Data
public class QtdTotalArrecadadaListagemDto {
    private Integer condominioId;
    private String nome;
    private Integer produtoId;
    private String qtdProdutos;
}
