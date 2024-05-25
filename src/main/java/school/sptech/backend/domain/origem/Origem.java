package school.sptech.backend.domain.origem;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.domain.condominio.Condominio;

import java.util.Date;

@Entity
@Getter
@Setter
public class Origem {

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
