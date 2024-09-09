package school.sptech.backend.domain.unidademedida;

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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeMedida extends BaseEntity {
    private String nome;
    private String representacao;

    @Override
    public String toString() {
        return "%s".formatted(representacao);
    }
}
