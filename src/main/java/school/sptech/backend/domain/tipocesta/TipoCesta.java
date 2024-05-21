package school.sptech.backend.domain.tipocesta;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import school.sptech.backend.domain.cesta.Cesta;

import java.util.List;

@Entity
@Getter
@Setter
public class TipoCesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "tipoCesta")
    private List<Cesta> cestas;
}
