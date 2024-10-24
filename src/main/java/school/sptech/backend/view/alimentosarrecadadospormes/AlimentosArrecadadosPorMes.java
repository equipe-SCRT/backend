package school.sptech.backend.view.alimentosarrecadadospormes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "v_qtd_total_alimentos_arrecadados_por_mes")
public class AlimentosArrecadadosPorMes {
    @Id
    private Integer qtdArrecadada;
    private Integer mes;
    private Integer ano;
}
