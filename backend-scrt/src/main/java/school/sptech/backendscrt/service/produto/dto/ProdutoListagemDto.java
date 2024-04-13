package school.sptech.backendscrt.service.produto.dto;

public class ProdutoListagemDto {
    private int id;
    private String nome;
    private int fkTipoProduto;

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

    public int getFkTipoProduto() {
        return fkTipoProduto;
    }

    public void setFkTipoProduto(int fkTipoProduto) {
        this.fkTipoProduto = fkTipoProduto;
    }
}
