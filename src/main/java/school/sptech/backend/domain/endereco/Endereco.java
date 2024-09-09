package school.sptech.backend.domain.endereco;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import school.sptech.backend.domain.BaseEntity;
import school.sptech.backend.domain.Timestamped;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Endereco extends BaseEntity {

    private String logradouro;
    private String bairro;
    private String numero;
    private String cep;
}
