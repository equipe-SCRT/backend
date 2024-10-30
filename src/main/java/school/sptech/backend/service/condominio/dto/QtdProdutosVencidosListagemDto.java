package school.sptech.backend.service.condominio.dto;

import lombok.Data;

@Data
public class QtdProdutosVencidosListagemDto {
    private Integer condominioId;
    private String nomeCondominio;
    private Integer qtdVencidos;
}
