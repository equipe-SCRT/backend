package school.sptech.backend.domain.condominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.backend.domain.BaseEntity;
import school.sptech.backend.domain.Timestamped;
import school.sptech.backend.domain.endereco.Endereco;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Condominio extends BaseEntity {

    private String nome;

    @ManyToOne
    private Endereco endereco;
}
