package school.sptech.backend.service.produtounitario.view;

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
@Table(name = "v_produtos_conforme_nao_conforme_campanhas")
public class ProdutosConformeNaoConformeCampanhas {
    @Id
    private String nome;
    private Integer conforme;
    private Integer naoConforme;
}
