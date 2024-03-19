package school.sptech.backendscrt.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;

public class Produto {
    private int idProduto;
    private String nome;
    private double preco;
    private LocalDate dataValidade;
    private double peso;
    private String codigoBarras;
    private LocalDate dataAdicionado;
    private String marca;
    List<TipoProduto> tipoProdutoList = new ArrayList<>();

    public Produto(){}

    public Produto(int idProduto, String nome, double preco, LocalDate dataValidade, double peso, String codigoBarras, LocalDate dataAdicionado, String marca, List<TipoProduto> tipoProdutoList) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.preco = preco;
        this.dataValidade = dataValidade;
        this.peso = peso;
        this.codigoBarras = codigoBarras;
        this.dataAdicionado = dataAdicionado;
        this.marca = marca;
        this.tipoProdutoList = tipoProdutoList;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
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

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public LocalDate getDataAdicionado() {
        return dataAdicionado;
    }

    public void setDataAdicionado(LocalDate dataAdicionado) {
        this.dataAdicionado = dataAdicionado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public List<TipoProduto> getTipoProdutoList() {
        return tipoProdutoList;
    }

    public void setTipoProdutoList(int valor) {
        if (valor == 0)
            tipoProdutoList.add(TipoProduto.PERECIVEL);
        else if (valor == 1)
            tipoProdutoList.add(TipoProduto.NAO_PERECIVEL);
        else if (valor == 2)
            tipoProdutoList.add(TipoProduto.ALIMENTICIO);
    }
}
