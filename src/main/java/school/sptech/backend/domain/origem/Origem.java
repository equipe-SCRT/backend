package school.sptech.backend.domain.origem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.backend.domain.Timestamped;
import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.domain.condominio.Condominio;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Origem extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer autaDeSouzaRua;
    private Integer itapora;


    @ManyToOne
    private Condominio condominio;

    @ManyToOne
    private Campanha campanha;
}
