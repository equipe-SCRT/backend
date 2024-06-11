package school.sptech.backend.domain.metrica;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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


    public Metrica(LocalDate alteracao, Usuario usuario) {
        this.alteracao = alteracao;
        this.usuario = usuario;
    }

    

    


    
}
