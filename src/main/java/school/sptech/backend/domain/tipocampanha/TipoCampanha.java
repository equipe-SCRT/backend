package school.sptech.backend.domain.tipocampanha;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.backend.domain.Timestamped;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoCampanha extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoCampanha;
    private String nome;

    @Override
    public String toString() {
        return "TipoCampanha{" +
                "idTipoCampanha=" + idTipoCampanha +
                ", nome='" + nome + '\'' +
                '}';
    }
}
