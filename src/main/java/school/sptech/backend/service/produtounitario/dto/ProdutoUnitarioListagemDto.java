package school.sptech.backend.service.produtounitario.dto;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import school.sptech.backend.domain.cesta.Cesta;
import school.sptech.backend.domain.metrica.Metrica;
import school.sptech.backend.domain.origem.Origem;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.rota.Rota;
import school.sptech.backend.domain.unidademedida.UnidadeMedida;

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

    private Rota rota;

    private Metrica metrica;

    @Data
    public static class Origem {
        private Integer id;
        private Integer autaDeSouzaRua;
        private Integer itapora;
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
    public static class Rota {
        private Integer id;
        private String nome;
        private String kmRodados;
        private Integer qtdColaboradores;
        private LocalDate dataHistorico;
        private Time horaInicio;
        private Time horaFim;
    }

    @Data
    public static class Metrica {
        private Integer id;
        private LocalDate alteracao;
    }

}
