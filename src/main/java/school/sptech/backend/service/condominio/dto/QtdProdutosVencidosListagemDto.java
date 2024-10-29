package school.sptech.backend.service.condominio.dto;

import lombok.Data;

@Data
public class QtdProdutosVencidosListagemDto {
    private String nomeCondominio;
    private Integer qtdVencidos;
}
