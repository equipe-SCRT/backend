package school.sptech.backend.service.tipocesta.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TipoCestaListagemDto {
    private Integer id;
    private String nome;
    private List<CestaDto> cestas;

    @Data
    public static class CestaDto{
        private Integer id;
        private String lote;
        private LocalDate dataMontagem;
    }
}
