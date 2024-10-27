package school.sptech.backend.domain.produtocesta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.backend.domain.BaseEntity;
import school.sptech.backend.domain.Timestamped;
import school.sptech.backend.domain.cesta.Cesta;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.tipocesta.TipoCesta;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoCesta extends BaseEntity {

    @ManyToOne
    private Produto Produto;
    @ManyToOne
    private TipoCesta tipoCesta;

    private Integer qtdProduto;
}
