package school.sptech.backend.domain.origem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.backend.domain.BaseEntity;
import school.sptech.backend.domain.Timestamped;
import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.domain.condominio.Condominio;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Origem extends BaseEntity {


    private Integer autaDeSouzaRua;
    private Integer itapora;


    @ManyToOne
    private Condominio condominio;

    @ManyToOne
    private Campanha campanha;
}
