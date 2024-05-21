package school.sptech.backend.domain.historicomudanca;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import school.sptech.backend.domain.usuario.entity.Usuario;

@Entity
@Getter
@Setter
public class HistoricoMudanca {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id_historico_mudanca;
    private LocalDate data_hora;
    @ManyToOne
    private Usuario Usuario;
}
