package school.sptech.backend.domain.campanha;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.backend.domain.Timestamped;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.tipocampanha.TipoCampanha;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Campanha extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String localCampanha;
    private LocalDate dataCampanha;
    private Integer qtdArrecadada;
    private Integer meta;
    @ManyToOne
    private TipoCampanha tipoCampanha;
    @ManyToOne
    private Produto produto;
}
