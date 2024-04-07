package school.sptech.backendscrt.domain.lote;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import school.sptech.backendscrt.domain.cesta.Cesta;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLote;
    private List<Cesta> cestas = new ArrayList<>();

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public List<Cesta> getCestas() {
        return cestas;
    }

    public void setCestas(List<Cesta> cestas) {
        this.cestas = cestas;
    }

    public void adicionarCesta(Cesta cesta) {
        cestas.add(cesta);
    }
}
