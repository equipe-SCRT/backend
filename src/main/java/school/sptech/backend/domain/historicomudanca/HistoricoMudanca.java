package school.sptech.backend.domain.historicomudanca;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import school.sptech.backend.domain.BaseEntity;
import school.sptech.backend.domain.Timestamped;
import school.sptech.backend.domain.usuario.Usuario;

@Entity
@Getter
@Setter
public class HistoricoMudanca extends BaseEntity {


    private LocalDate dataHora;
    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    public HistoricoMudanca(Integer id, LocalDate dataHora, Usuario usuario) {
        this.id = id;
        this.dataHora = dataHora;
        this.usuario = usuario;
    }

    public HistoricoMudanca(LocalDate dataHora, Usuario usuario) {
        this.dataHora = dataHora;
        this.usuario = usuario;
    }

    

    public HistoricoMudanca(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public HistoricoMudanca() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HistoricoMudanca{");
        sb.append("id=").append(id);
        sb.append(", dataHora=").append(dataHora);
        sb.append(", usuario=").append(usuario);
        sb.append('}');
        return sb.toString();
    }
}
