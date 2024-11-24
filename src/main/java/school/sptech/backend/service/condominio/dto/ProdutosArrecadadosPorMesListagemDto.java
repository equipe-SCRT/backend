package school.sptech.backend.service.condominio.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProdutosArrecadadosPorMesListagemDto {

    private LocalDate criadoEm;
    private Integer count;
}
