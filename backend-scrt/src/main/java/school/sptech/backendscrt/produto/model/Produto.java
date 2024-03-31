package school.sptech.backendscrt.produto.model;

import java.time.LocalDate;

public class Produto {
    private int idProduto;
    private String nomeProduto;
    private LocalDate dataValidade;
    private double preco;
    private int tipoProduto;
    private int fkOrigem;

    public Produto(int idProduto, String nomeProduto, LocalDate dataValidade, double preco) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.dataValidade = dataValidade;
        this.preco = preco;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(int tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public int getFkOrigem() {
        return fkOrigem;
    }

    public void setFkOrigem(int fkOrigem) {
        this.fkOrigem = fkOrigem;
    }
}
