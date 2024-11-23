package school.sptech.backend.domain.produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.backend.domain.Timestamped;
import school.sptech.backend.domain.tipoproduto.TipoProduto;
import school.sptech.backend.domain.unidademedida.UnidadeMedida;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private int qtdUnidadeMedida;

    @ManyToOne
    private TipoProduto tipoProduto;

    @ManyToOne
    private UnidadeMedida unidadeMedida;
}
