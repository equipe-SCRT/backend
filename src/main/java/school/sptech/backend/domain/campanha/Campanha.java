package school.sptech.backend.domain.campanha;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import school.sptech.backend.domain.BaseEntity;
import school.sptech.backend.domain.Timestamped;

import java.util.Date;

@Entity
@Getter
@Setter
public class Campanha extends BaseEntity {

    private String nome;
    private String localCampanha;
    private Date dataCampanha;
    private Integer qtdArrecadada;
    private Integer meta;
}
