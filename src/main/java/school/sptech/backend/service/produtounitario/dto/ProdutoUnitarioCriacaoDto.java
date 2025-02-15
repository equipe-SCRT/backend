package school.sptech.backend.service.produtounitario.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProdutoUnitarioCriacaoDto {
//    @NotBlank
//    private String nome;

    @NotNull
    @FutureOrPresent
    private LocalDate dataValidade;

//    @NotNull
//    @PositiveOrZero
//    private Double peso;

    @NotNull
    private boolean ativo;

    @NotNull
    private boolean confome;

    @NotNull
    @PositiveOrZero
    private Integer origemId;

//    @NotNull
//    @PositiveOrZero
//    private Integer unidadeMedidaId;

//    @NotNull
//    @PositiveOrZero
//    private Integer cestaId;

    @NotNull
    @PositiveOrZero
    private Integer produtoId;

    @NotNull
    @Positive
    private Integer quantidade;

//    @NotNull
//    @PositiveOrZero
//    private Integer rotaId;
//
//    @NotNull
//    @PositiveOrZero
//    private Integer metricaId;
}
