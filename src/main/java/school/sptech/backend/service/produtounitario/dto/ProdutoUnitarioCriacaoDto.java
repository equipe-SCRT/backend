package school.sptech.backend.service.produtounitario.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import school.sptech.backend.domain.cesta.Cesta;
import school.sptech.backend.domain.metrica.Metrica;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.rota.Rota;

import java.time.LocalDate;

@Data
public class ProdutoUnitarioCriacaoDto {
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
