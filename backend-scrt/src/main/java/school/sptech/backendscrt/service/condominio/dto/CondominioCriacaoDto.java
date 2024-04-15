package school.sptech.backendscrt.service.condominio.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class CondominioCriacaoDto {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String getNomeCondominio;
    @PositiveOrZero
    private int fkEndereco;

    public String getNomeCondominio() {
        return getNomeCondominio;
    }

    public void setGetNomeCondominio(String getNomeCondominio) {
        this.getNomeCondominio = getNomeCondominio;
    }

    public int getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(int fkEndereco) {
        this.fkEndereco = fkEndereco;
    }
}
