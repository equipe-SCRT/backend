package school.sptech.backend.service.condominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CondominioCriacaoDto {

    @NotBlank
    @Size(min = 5, max = 50)
    private String nome;
    @NotNull
    @Positive
    private Integer id;
}
