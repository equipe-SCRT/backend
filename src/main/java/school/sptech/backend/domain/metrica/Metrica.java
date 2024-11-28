package school.sptech.backend.domain.metrica;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.backend.domain.Timestamped;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Metrica extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer qtdCasas;
    private Integer alertaVencimento;

}
