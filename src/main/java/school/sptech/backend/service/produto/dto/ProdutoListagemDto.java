package school.sptech.backend.service.produto.dto;

public class ProdutoListagemDto {
    private Integer id;
    private String nome;
    private int fkTipoProduto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
