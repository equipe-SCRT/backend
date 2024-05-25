package school.sptech.backend.service.historicomudanca.dto;

import java.time.LocalDate;

import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


public class HistoricoMudancaCriacaoDto {

    private LocalDate dataHora;


    private Long fkUsuario;


    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public Long getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Long fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
