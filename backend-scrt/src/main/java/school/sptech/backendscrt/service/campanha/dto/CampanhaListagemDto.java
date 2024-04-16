package school.sptech.backendscrt.service.campanha.dto;


import java.time.LocalDate;

public class CampanhaListagemDto {
    private int id;
    private String nome;
    private String local;
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
