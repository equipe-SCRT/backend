package school.sptech.backend.domain.cesta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.backend.domain.Timestamped;
import school.sptech.backend.domain.tipocesta.TipoCesta;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cesta  extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lote;
    private LocalDate dataMontagem;

    @ManyToOne
    private TipoCesta tipoCesta;
}
