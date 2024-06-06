package school.sptech.backend.service.endereco.dto;

import lombok.Data;

@Data
public class EnderecoCriacaoDto {
    private String logradouro;
    private String numero;
    private String cep;
}
