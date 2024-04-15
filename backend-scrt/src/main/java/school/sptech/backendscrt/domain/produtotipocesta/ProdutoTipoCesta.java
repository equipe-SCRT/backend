package school.sptech.backendscrt.domain.produtotipocesta;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProdutoTipoCesta {
    @Id
    private int fkProfuto;
    private int fkTipoCesta;
}
