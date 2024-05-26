package school.sptech.backend.domain.metrica;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import school.sptech.backend.domain.usuario.entity.Usuario;

@Entity
@Getter
@Setter
public class Metrica {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int idMetrica;
    private LocalDate alteracao;
    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;
}
