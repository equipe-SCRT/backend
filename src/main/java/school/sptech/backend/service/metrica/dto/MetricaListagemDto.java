package school.sptech.backend.service.metrica.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MetricaListagemDto {
    private int idMetrica;
    private LocalDate alteracao;
    private long fkUsuario;
}
