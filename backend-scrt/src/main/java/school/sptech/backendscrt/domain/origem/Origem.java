package school.sptech.backendscrt.domain.origem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Origem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int autaDeSousaRua;
    private int itapora;
    private int fkCondominio;
    private int fkCampanha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
