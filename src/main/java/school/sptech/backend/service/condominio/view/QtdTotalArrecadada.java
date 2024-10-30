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
@Table(name = "v_qtd_produto_por_condominio")
public class QtdTotalArrecadada {

    @Id
    private Integer produtoId;
    private String nomeCondominio;
    private String qtdProdutos;
}