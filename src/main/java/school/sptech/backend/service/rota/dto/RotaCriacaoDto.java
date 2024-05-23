package school.sptech.backend.service.rota.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
public class RotaCriacaoDto {

    @NotBlank
    @Size(min = 5, max = 50)
    private String nome;
    @Size(min = 5, max = 10)
    private String km_rodados;
    @PositiveOrZero
    private Integer qtd_colaboradores;
    @PastOrPresent
    private LocalDate data_historico;
    private Time hora_inicio;
    private Time hora_fim;
}
