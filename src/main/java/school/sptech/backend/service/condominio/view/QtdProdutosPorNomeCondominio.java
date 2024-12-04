package school.sptech.backend.service.condominio.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "v_qtd_produtos_por_nome_condominio")

public class QtdProdutosPorNomeCondominio {

    @Id
    private Integer id;
    private LocalDate criadoEm;
    private String nomeCondominio;
    private Integer count;

}
