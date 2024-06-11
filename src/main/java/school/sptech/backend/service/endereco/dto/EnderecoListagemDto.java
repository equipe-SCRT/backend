package school.sptech.backend.service.endereco.dto;

import lombok.Data;

@Data
public class EnderecoListagemDto {
    private String id;
    private String logradouro;
    private String numero;
    private String cep;
}
