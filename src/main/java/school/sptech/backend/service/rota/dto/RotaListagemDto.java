package school.sptech.backend.service.rota.dto;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RotaListagemDto {

    private Integer id;
    private String nome;
    private String kmRodados;
    private Integer qtdColaboradores;
    private LocalDate dataHistorico;
    private Time horaInicio;
    private Time horaFim;
}
