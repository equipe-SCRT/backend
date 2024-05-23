package school.sptech.backend.domain.produtocesta.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import school.sptech.backend.domain.produto.Produto;

@Entity
@Getter
@Setter
public class ProdutoCesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProdutoCesta;
    @ManyToOne
    private Produto Produto;
    private Integer Cesta;
}
