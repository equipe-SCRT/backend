package school.sptech.backend.service.historicomudanca.dto;

import java.time.LocalDate;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import school.sptech.backend.domain.usuario.entity.Usuario;


@Data
public class HistoricoMudancaListagemDto {

    private LocalDate dataHora;
    private Usuario usuario;

    @Data
    public static class Usuario {
        private String nome;
        private String email;
        private Integer tipoUsuario;
    }
    
}
