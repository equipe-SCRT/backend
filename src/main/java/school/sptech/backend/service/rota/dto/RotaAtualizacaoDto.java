package school.sptech.backend.service.rota.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RotaAtualizacaoDto {

    @NotBlank
    @Size(min = 5, max = 50)
    private String nome;
    @Positive
    private Integer kmRodados;
    @Positive
    private Integer qtdColaboradores;
    @PastOrPresent
    private LocalDate dataHistorico;
    private Time horaInicio;
    private Time horaFim;
}
