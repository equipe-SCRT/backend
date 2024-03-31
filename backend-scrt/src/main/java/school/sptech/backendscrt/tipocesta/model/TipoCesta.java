package school.sptech.backendscrt.tipocesta.model;

import school.sptech.backendscrt.produto.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class TipoCesta {
    private int idTipoCesta;
    private String nomeTipoCesta;
    private List<Produto> produtos = new ArrayList<>();

    public int getIdTipoCesta() {
        return idTipoCesta;
    }

    public void setIdTipoCesta(int idTipoCesta) {
        this.idTipoCesta = idTipoCesta;
    }

    public String getNomeTipoCesta() {
        return nomeTipoCesta;
    }

    public void setNomeTipoCesta(String nomeTipoCesta) {
        this.nomeTipoCesta = nomeTipoCesta;
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
