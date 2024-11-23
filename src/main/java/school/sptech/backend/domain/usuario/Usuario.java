package school.sptech.backend.domain.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.sptech.backend.domain.Timestamped;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String nome;
    @NotNull
    private String email;
    @NotNull
    private String senha;
    @NotNull
    private Integer tipoUsuario;

}
