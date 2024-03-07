package school.sptech.backendscrt;

public enum TipoProduto {
    PERECIVEL(0),
    NAO_PERECIVEL(1),
    ALIMENTICIO(2);

    public int idProduto;
    TipoProduto(int idProduto){
        this.idProduto = idProduto;
    }
}
