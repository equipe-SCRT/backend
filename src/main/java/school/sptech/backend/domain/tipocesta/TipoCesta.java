package school.sptech.backend.domain.tipocesta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import school.sptech.backend.domain.Timestamped;
import school.sptech.backend.domain.tipoproduto.TipoProduto;

import java.util.List;

@Entity
@Getter
@Setter
public class TipoCesta extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @OneToMany
    private List<TipoProduto> tipoProduto;
}
