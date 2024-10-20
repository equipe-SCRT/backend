package school.sptech.backend.service.campanha.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CampanhaAtualizacaoDto {

    @NotBlank
    @Size(min = 2, max = 40)
    private String localCampanha;
    @PastOrPresent
    private Date dataCampanha;
    @NotNull
    @Positive
    private Integer qtdArrecadada;
    @NotNull
    @Positive
    private Integer meta;
}
