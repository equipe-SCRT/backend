package school.sptech.backend.service.produtounitario.dto;

import lombok.Data;
import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.domain.condominio.Condominio;

import java.sql.Time;
import java.time.LocalDate;

@Data
public class ProdutoUnitarioListagemDto {
    private Integer id;

    private String nome;

    private LocalDate dataValidade;

    private Double peso;

    private boolean ativo;
    private boolean vencido;
    private boolean confome;

    private Origem origem;

    private UnidadeMedida unidadeMedida;

    private Cesta cesta;

    private Produto produto;

    private Metrica metrica;

    @Data
    public static class Origem {
        private Integer id;
        private Integer autaDeSouzaRua;
        private Integer itapora;
        private Condominio condominio;
        private Campanha campanha;
    }

    @Data
    public static class UnidadeMedida {
        private Integer id;
        private String nome;
        private String representacao;
    }

    @Data
    public static class Cesta {
        private Long id;
        private String lote;
        private LocalDate dataMontagem;
    }

    @Data
    public static class Produto {
        private Integer id;
        private String nome;
    }

    @Data
    public static class Metrica {
        private Integer id;
        private LocalDate alteracao;
    }

}
