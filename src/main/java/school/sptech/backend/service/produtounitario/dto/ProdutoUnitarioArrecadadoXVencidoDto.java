package school.sptech.backend.service.produtounitario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProdutoUnitarioArrecadadoXVencidoDto {
    private String nome;
    private Long arrecadado;
    private Long vencido;
}
