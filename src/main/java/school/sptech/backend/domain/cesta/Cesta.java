package school.sptech.backend.domain.cesta;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
    private Integer id;
    private String lote;
    @PastOrPresent
    private LocalDate dataMontagem;

    @ManyToOne
    @NotNull
    private TipoCesta tipoCesta;
}
