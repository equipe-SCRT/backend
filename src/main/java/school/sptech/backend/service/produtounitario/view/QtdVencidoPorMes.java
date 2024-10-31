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
@Table(name = "v_produto_unitario_qtd_vencido_por_mes")
public class QtdVencidoPorMes {
    @Id
    private Integer produtoId;
    private Integer qtd;
    private LocalDate dataValidade;


}
