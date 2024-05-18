package school.sptech.backend.service.tipocesta.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TipoCestaListagemDto {
    private Long id;
    private String nome;
    private List<CestaDto> cestas;

    @Data
    public static class CestaDto{
        private Long id;
        private String lote;
        private LocalDate dataMontagem;
    }
}
