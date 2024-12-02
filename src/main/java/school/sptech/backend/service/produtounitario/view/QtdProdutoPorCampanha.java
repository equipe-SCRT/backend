package school.sptech.backend.service.produtounitario.view;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "v_qtd_produto_por_campanha")
public class QtdProdutoPorCampanha {
    @Id
    private Integer id;
    private Integer campanhaId;
    private Integer produtoId;
    private String nome;
    private Integer qtdProdutos;

}
