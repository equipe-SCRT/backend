package school.sptech.backendscrt.service.rua.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import school.sptech.backendscrt.domain.endereco.Endereco;
import school.sptech.backendscrt.domain.rua.Rua;

import java.util.ArrayList;
import java.util.List;

public class RuaCriacaoDto {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String nomeRua;
    @NotNull
    private List<Endereco> enderecos = new ArrayList<>();
    @PositiveOrZero
    private int fkRota;

    public String getNomeRua() {
        return nomeRua;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public int getFkRota() {
        return fkRota;
    }

    public void setFkRota(int fkRota) {
        this.fkRota = fkRota;
    }
}
