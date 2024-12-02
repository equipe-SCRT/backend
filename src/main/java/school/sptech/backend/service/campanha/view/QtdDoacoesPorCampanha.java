package school.sptech.backend.service.campanha.view;

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
@Table(name = "v_qtd_doacoes_por_campanha")
public class QtdDoacoesPorCampanha {
    @Id
    private Integer id;
    private Integer qtdArrecadada;
    private LocalDate dataCampanha;
    private String nome;
}
