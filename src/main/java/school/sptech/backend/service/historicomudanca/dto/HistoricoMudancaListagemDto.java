package school.sptech.backend.service.historicomudanca.dto;

import java.time.LocalDate;

import lombok.Data;


@Data
public class HistoricoMudancaListagemDto {

    private int idHistoricoMudanca;
    private LocalDate dataHora;
    private long fkUsuario;
    
}
