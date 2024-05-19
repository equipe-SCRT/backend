package school.sptech.backend.service.metrica.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MetricaListagemDto {
    private int id_metrica;
    private LocalDate alteracao;
    private int fk_usuario;
}
