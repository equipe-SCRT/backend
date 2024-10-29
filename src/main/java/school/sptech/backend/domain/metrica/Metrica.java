package school.sptech.backend.domain.metrica;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import school.sptech.backend.domain.BaseEntity;
import school.sptech.backend.domain.Timestamped;
import school.sptech.backend.domain.usuario.Usuario;

@Entity
@Getter
@Setter
public class Metrica extends BaseEntity {

    private Integer qtdCasas;
    private Integer alertaVencimento;

}
