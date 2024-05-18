package school.sptech.backend.service.cesta.dto;

import lombok.Data;
import school.sptech.backend.domain.tipocesta.TipoCesta;

import java.time.LocalDate;
@Data
public class CestaListagemDto {
    private Long id;
    private String lote;
    private LocalDate dataMontagem;

    private TipoCestaDto tipoCesta;

    @Data
    public static class TipoCestaDto{
        private Long id;
        private String nome;
    }
}
