package school.sptech.backend.domain.cesta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import school.sptech.backend.domain.tipocesta.TipoCesta;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Cesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loteId;
    private LocalDate dataMontagem;

    @ManyToOne
    private TipoCesta tipoCesta;
}
