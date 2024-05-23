package school.sptech.backend.domain.origem;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.domain.condominio.Condominio;

import java.util.Date;

@Getter
@Setter
public class Origem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer auta_de_souza_rua;
    private Integer itapora;


    @ManyToOne
    private Condominio comdominio;

    @ManyToOne
    private Campanha campanha;
}
