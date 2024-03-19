package school.sptech.backendscrt.Model;

import java.util.ArrayList;
import java.util.List;

public class Rota {
    private int idRota;
    private String nomeRota;
    private List<Rua> ruas = new ArrayList<>();

    public int getIdRota() {
        return idRota;
    }

    public void setIdRota(int idRota) {
        this.idRota = idRota;
    }

    public String getNomeRota() {
        return nomeRota;
    }

    public void setNomeRota(String nomeRota) {
        this.nomeRota = nomeRota;
    }

    public List<Rua> getRuas() {
        return ruas;
    }

    public void setRuas(List<Rua> ruas) {
        this.ruas = ruas;
    }

    public void adicionarRua(Rua rua) {
        ruas.add(rua);
    }
}
