package school.sptech.backend.domain.tipocesta;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import school.sptech.backend.domain.Timestamped;
import school.sptech.backend.domain.cesta.Cesta;
import school.sptech.backend.domain.produto.Produto;
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
