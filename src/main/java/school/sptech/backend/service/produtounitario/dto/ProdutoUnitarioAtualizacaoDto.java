package school.sptech.backend.service.produtounitario.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import school.sptech.backend.domain.origem.Origem;
import school.sptech.backend.domain.unidademedida.UnidadeMedida;

import java.time.LocalDate;

@Data
public class ProdutoUnitarioAtualizacaoDto {
    @NotBlank
    private String nome;

    @NotNull
    @FutureOrPresent
    private LocalDate dataValidade;

    @NotNull
    @PositiveOrZero
    private Double peso;

    @NotNull
    private boolean ativo;

    @NotNull
    @Positive
    private Integer origemId;

    @NotNull
    @Positive
    private Integer unidadeMedidaId;

//    @NotNull
//    @Positive
//    private Integer cestaId;

    @NotNull
    @Positive
    private Integer produtoId;

//    @NotNull
//    @Positive
//    private Integer rotaId;
//
//    @NotNull
//    @Positive
//    private Integer metricaId;
}
