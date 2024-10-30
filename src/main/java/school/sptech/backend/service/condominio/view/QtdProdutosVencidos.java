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
@Table(name = "v_qtd_produtos_vencidos_por_condominio")
public class QtdProdutosVencidos {

    @Id
    private Integer condominioId;
    private String nomeCondominio;
    private Integer qtdVencidos;
}