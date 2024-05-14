package school.sptech.backend.domain.produtocesta.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProdutoCesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProdutoCesta;
    @ManyToOne
    private Integer idProduto;
    @ManyToOne
    private Integer idCesta;
}
