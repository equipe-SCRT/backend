package school.sptech.backend.domain.produtocesta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.backend.domain.cesta.Cesta;
import school.sptech.backend.domain.produto.Produto;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoCesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Produto Produto;
    @ManyToOne
    private Cesta Cesta;
}
