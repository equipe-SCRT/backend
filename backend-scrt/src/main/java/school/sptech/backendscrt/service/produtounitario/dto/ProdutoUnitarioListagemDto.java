package school.sptech.backendscrt.service.produtounitario.dto;


import java.time.LocalDate;

public class ProdutoUnitarioListagemDto {
    private int id;
    private String nome;
    private LocalDate dataValidade;
    private double peso;
    private int fkOrigem;
    private int fkUnidadeMedida;
    private int fkCesta;
    private int fkProduto;
    private int fkRota;

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

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getFkOrigem() {
        return fkOrigem;
    }

    public void setFkOrigem(int fkOrigem) {
        this.fkOrigem = fkOrigem;
    }

    public int getFkUnidadeMedida() {
        return fkUnidadeMedida;
    }

    public void setFkUnidadeMedida(int fkUnidadeMedida) {
        this.fkUnidadeMedida = fkUnidadeMedida;
    }

    public int getFkCesta() {
        return fkCesta;
    }

    public void setFkCesta(int fkCesta) {
        this.fkCesta = fkCesta;
    }

    public int getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(int fkProduto) {
        this.fkProduto = fkProduto;
    }

    public int getFkRota() {
        return fkRota;
    }

    public void setFkRota(int fkRota) {
        this.fkRota = fkRota;
    }
}
