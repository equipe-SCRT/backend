package school.sptech.backendscrt.Model;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private int idEstoque;
    private String nome;
    private List<Produto> produtos = new ArrayList<>();

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void addProdutos(Produto produto){
        produtos.add(produto);
    }
}
