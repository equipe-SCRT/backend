package school.sptech.backend.service.campanha.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class CampanhaAtualizacaoDto {

    @NotBlank
    @Size(min = 2, max = 40)
    private String localCampanha;
    @PastOrPresent
    private LocalDate dataCampanha;
    @NotNull
    @Positive
    private Integer qtdArrecadada;
    @NotNull
    @Positive
    private Integer meta;
}
