package school.sptech.backend.service.historicomudanca.dto;

import java.time.LocalDate;

import lombok.Data;


@Data
public class HistoricoMudancaListagemDto {

    private Integer idHistoricoMudanca;
    private LocalDate dataHora;
    private Usuario usuario;

    @Data
    public static class Usuario {
        private Integer idUsuario;
        private String nome;
        private String email;
        private Integer tipoUsuario;
    }
    
}
