package school.sptech.backendscrt.service.origem.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class OrigemAtualizacaoDto {
    @NotNull
    @PositiveOrZero
    private int autaDeSousaRua;
    @NotNull
    @PositiveOrZero
    private int itapora;
    @NotNull
    @PositiveOrZero
    private int fkCondominio;
    @NotNull
    @PositiveOrZero
    private int fkCampanha;

    public int getAutaDeSousaRua() {
        return autaDeSousaRua;
    }

    public void setAutaDeSousaRua(int autaDeSousaRua) {
        this.autaDeSousaRua = autaDeSousaRua;
    }

    public int getItapora() {
        return itapora;
    }

    public void setItapora(int itapora) {
        this.itapora = itapora;
    }

    public int getFkCondominio() {
        return fkCondominio;
    }

    public void setFkCondominio(int fkCondominio) {
        this.fkCondominio = fkCondominio;
    }

    public int getFkCampanha() {
        return fkCampanha;
    }

    public void setFkCampanha(int fkCampanha) {
        this.fkCampanha = fkCampanha;
    }
}
