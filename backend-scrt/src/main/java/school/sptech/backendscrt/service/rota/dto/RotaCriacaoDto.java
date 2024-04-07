package school.sptech.backendscrt.service.rota.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import school.sptech.backendscrt.domain.rua.Rua;

import java.util.ArrayList;
import java.util.List;

public class RotaCriacaoDto {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String nomeRota;
    @NotNull
    private List<Rua> ruas = new ArrayList<>();

    public String getNomeRota() {
        return nomeRota;
    }

    public void setNomeRota(String nomeRota) {
        this.nomeRota = nomeRota;
    }

    public List<Rua> getRuas() {
        return ruas;
    }

    public void setRuas(List<Rua> ruas) {
        this.ruas = ruas;
    }
}
