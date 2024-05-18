package school.sptech.backend.domain.rota;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String km_rodados;
    private Integer qtd_colaboradores;
    private LocalDate data_historico;
    private Time hora_inicio;
    private Time hora_fim;
}
