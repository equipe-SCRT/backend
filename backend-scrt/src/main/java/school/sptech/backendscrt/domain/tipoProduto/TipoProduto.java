package school.sptech.backendscrt.domain.tipoProduto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class TipoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoProduto;
    private String nometipoProduto;

    public int getIdTipoProduto() {
        return idTipoProduto;
    }

    public void setIdTipoProduto(int idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }

    public String getNometipoProduto() {
        return nometipoProduto;
    }

    public void setNometipoProduto(String nometipoProduto) {
        this.nometipoProduto = nometipoProduto;
    }
}
