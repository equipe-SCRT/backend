package school.sptech.backend.service.campanha.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CampanhaCriacaoDto {

    @NotBlank
    @Size(min = 2, max = 20)
    private String nome;
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
