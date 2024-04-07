package school.sptech.backendscrt.domain.tipoCesta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import school.sptech.backendscrt.domain.produto.Produto;

import java.util.ArrayList;
import java.util.List;
@Entity
public class TipoCesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
