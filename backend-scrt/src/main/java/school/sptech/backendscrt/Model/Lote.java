package school.sptech.backendscrt.Model;

import java.util.ArrayList;
import java.util.List;

public class Lote {
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
