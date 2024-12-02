package school.sptech.backend.service.produtounitario.view;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@Table(name = "v_qtd_produtos_vencidos_por_campanha")

public class QtdProdutosVencidosPorCampanha {
    @Id
    private Integer id;
    private Integer campanhaId;
    private Integer produtoId;
    private String nome;
    private Integer qtdProdutosVencidos;    

}
