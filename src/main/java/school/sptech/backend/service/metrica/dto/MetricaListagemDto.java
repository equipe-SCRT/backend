package school.sptech.backend.service.metrica.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MetricaListagemDto {

    private LocalDate alteracao;
    private Usuario usuario;

    @Data
    public static class Usuario {
        private String nome;
        private String email;
        private Integer tipoUsuario;
    }

}
