package school.sptech.backendscrt;

import java.util.ArrayList;
import java.util.List;

public class Cesta {
    private int idCesta;
    private String nomeCesta;
    private List<Produto> produtos = new ArrayList<>();

    public int getIdCesta() {
        return idCesta;
    }

    public void setIdCesta(int idCesta) {
        this.idCesta = idCesta;
    }

    public String getNomeCesta() {
        return nomeCesta;
    }

    public void setNomeCesta(String nomeCesta) {
        this.nomeCesta = nomeCesta;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }
}
