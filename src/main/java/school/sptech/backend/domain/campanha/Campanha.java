package school.sptech.backend.domain.campanha;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.backend.domain.BaseEntity;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.tipocampanha.TipoCampanha;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Campanha extends BaseEntity {

    private String localCampanha;
    private Date dataCampanha;
    private Integer qtdArrecadada;
    private Integer meta;
    @ManyToOne
    private TipoCampanha tipoCampanha;
    @ManyToOne
    private Produto produto;
}
