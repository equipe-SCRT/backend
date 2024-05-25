package school.sptech.backend.domain.metrica;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    private Usuario usuario;
    
}
