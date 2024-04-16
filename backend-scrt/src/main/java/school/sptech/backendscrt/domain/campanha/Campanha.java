package school.sptech.backendscrt.domain.campanha;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
public class Campanha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String local;
    @CreationTimestamp
    private LocalDate dataCampanha;
    private int qtdArrecadada;
    private int meta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }
}
