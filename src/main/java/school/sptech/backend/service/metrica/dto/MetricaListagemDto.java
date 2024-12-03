package school.sptech.backend.service.metrica.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MetricaListagemDto {


    private Integer idMetrica;
    private LocalDate alteracao;
    private Usuario usuario;
    private Integer qtdCasas;

    @Data
    public static class Usuario {
        private Integer idUsuario;
        private String nome;
        private String email;
        private Integer tipoUsuario;
    }

}
