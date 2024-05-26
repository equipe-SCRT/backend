package school.sptech.backend.service.cesta.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class CestaListagemDto {
    private Integer id;
    private String lote;
    private LocalDate dataMontagem;

    private TipoCestaDto tipoCesta;

    @Data
    public static class TipoCestaDto{
        private Integer id;
        private String nome;
    }
}
