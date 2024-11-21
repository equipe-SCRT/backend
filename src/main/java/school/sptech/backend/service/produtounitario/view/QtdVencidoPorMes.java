package school.sptech.backend.service.produtounitario.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "v_produto_unitario_qtd_vencido_por_mes")
public class QtdVencidoPorMes {
    @Id
    private Integer qtd;
    private LocalDate dataValidade;
    private Integer produtoId;


}
