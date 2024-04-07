package school.sptech.backendscrt.domain.campanha;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
@Entity
public class Campanha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCampanha;
    private String nome;
    private String local;
    private LocalDate dataCampanha;
    private int qtdArrecadada;

    public int getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(int idCampanha) {
        this.idCampanha = idCampanha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDate getDataCampanha() {
        return dataCampanha;
    }

    public void setDataCampanha(LocalDate dataCampanha) {
        this.dataCampanha = dataCampanha;
    }

    public int getQtdArrecadada() {
        return qtdArrecadada;
    }

    public void setQtdArrecadada(int qtdArrecadada) {
        this.qtdArrecadada = qtdArrecadada;
    }
}
