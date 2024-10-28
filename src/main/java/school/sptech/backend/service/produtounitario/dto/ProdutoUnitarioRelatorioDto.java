package school.sptech.backend.service.produtounitario.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;


@Data
public class ProdutoUnitarioRelatorioDto {

    private ProdutoUnitarioListagemDto.Produto produto;

    private LocalDate dataValidade;

    private Double peso;

    private ProdutoUnitarioListagemDto.UnidadeMedida unidadeMedida;

    private ProdutoUnitarioListagemDto.Origem origem;

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
