package school.sptech.backend.domain.rota;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.backend.domain.BaseEntity;
import school.sptech.backend.domain.Timestamped;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rota extends BaseEntity {


    private String nome;
    private Integer kmRodados;
    private Integer qtdColaboradores;
    private LocalDate dataHistorico;
    private Time horaInicio;
    private Time horaFim;
}
