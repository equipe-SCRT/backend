package school.sptech.backend.service.campanha.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CampanhaAtualizacaoDto {

    @NotBlank
    @Size(min = 2, max = 20)
    private String nome;
    @NotBlank
    @Size(min = 2, max = 40)
    private String local_campanha;
    @PastOrPresent
    private Date data_campanha;
    @NotNull
    @Positive
    private Integer qtd_arrecadada;
    @NotNull
    @Positive
    private Integer meta;
}
