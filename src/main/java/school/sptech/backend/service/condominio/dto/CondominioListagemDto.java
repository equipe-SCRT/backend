package school.sptech.backend.service.condominio.dto;

import lombok.Data;

@Data
public class CondominioListagemDto {

    private Integer id;
    private String nome;
    private EnderecoDto endereco;

    @Data
    public static class EnderecoDto {
        private Integer id;
        private String logradouro;
        private String bairro;
        private String numero;
        private String cep;
        private Integer rotaId;
    }
}
