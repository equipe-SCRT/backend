package school.sptech.backend.service.produtounitario.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProdutoUnitarioAtualizacaoDto {
    @NotBlank
    @Size
    private String nome;

    @NotNull
    @FutureOrPresent
    private LocalDate dataValidade;

    @NotNull
    @PositiveOrZero
    private Double peso;

    private Boolean ativo;

    @NotNull
    @PositiveOrZero
    private Integer origemId;

    @NotNull
    @PositiveOrZero
    private Integer unidadeMedidaId;

    @NotNull
    @PositiveOrZero
    private Integer cestaId;

    @NotNull
    @PositiveOrZero
    private Integer produtoId;

    @NotNull
    @PositiveOrZero
    private Integer rotaId;

    @NotNull
    @PositiveOrZero
    private Integer metricaId;
}
