package school.sptech.backend.service.campanha.view;

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
@Table(name = "v_qtd_doacoes_por_campanha")
public class QtdDoacoesPorCampanha {
    @Id
    private Integer qtdArrecadada;
    private String nome;
    private Integer mes;
    private Integer ano;
}
