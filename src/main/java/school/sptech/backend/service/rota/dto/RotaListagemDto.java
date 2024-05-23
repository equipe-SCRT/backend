package school.sptech.backend.service.rota.dto;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
public class RotaListagemDto {

    private Integer id;
    private String nome;
    private String km_rodados;
    private Integer qtd_colaboradores;
    private LocalDate data_historico;
    private Time hora_inicio;
    private Time hora_fim;
}
