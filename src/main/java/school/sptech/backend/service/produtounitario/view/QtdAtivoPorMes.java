package school.sptech.backend.service.produtounitario.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "v_produto_unitario_qtd_ativo_por_mes")
public class QtdAtivoPorMes {
    @Id
    private Integer id;
    private LocalDate criadoEm;
    private Integer produtoId;
    private Integer qtd;

}
