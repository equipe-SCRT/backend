package school.sptech.backend.service.origem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import school.sptech.backend.domain.endereco.Endereco;

import java.util.Date;


@Data
public class OrigemListagemDto {

    private Integer id;
    private Integer autaDeSouzaRua;
    private Integer itapora;

    private CondominioDto condominio;

    private CampanhaDto campanha;


    @Data
    public static class CondominioDto {
        private Integer id;
        private String nome;
        private Endereco endereco;
    }

    @Data
    public static class CampanhaDto {
        private Integer id;
        private String nome;
        private String local_campanha;
        private Date data_campanha;
        private Integer qtd_arrecadada;
        private Integer meta;
    }
}
