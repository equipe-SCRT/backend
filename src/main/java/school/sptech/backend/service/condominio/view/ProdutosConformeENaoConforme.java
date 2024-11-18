package school.sptech.backend.service.condominio.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "v_qtd_discrepancia_condominios")
public class ProdutosConformeENaoConforme {

    @Id
    private Integer discrepancia;
    private String nomeCondominio;
    private String nomeProduto;
    private Integer qtdConforme;
    private Integer qtdNaoConforme;
}
