package school.sptech.backend.service.historicomudanca.dto;

import java.time.LocalDate;

import lombok.Data;


@Data
public class HistoricoMudancaListagemDto {

    private int id_historico_mudanca;
    private LocalDate data_hora;
    private int fk_usuario;
    
}
