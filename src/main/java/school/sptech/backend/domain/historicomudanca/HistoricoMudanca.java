package school.sptech.backend.domain.historicomudanca;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import school.sptech.backend.domain.usuario.entity.Usuario;

@Entity
@Getter
@Setter
public class HistoricoMudanca {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int idHistoricoMudanca;
    private LocalDate dataHora;
    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HistoricoMudanca{");
        sb.append("idHistoricoMudanca=").append(idHistoricoMudanca);
        sb.append(", dataHora=").append(dataHora);
        sb.append(", usuario=").append(usuario);
        sb.append('}');
        return sb.toString();
    }
}
