package school.sptech.backend.service.produtounitario.dto;

import lombok.Data;

@Data
public class VencidoArrecadadoDto {
    private String nome;
    private Integer vencido;
    private Integer arrecadado;
}
