package school.sptech.backendscrt.domain.rota;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import school.sptech.backendscrt.domain.rua.Rua;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Rota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
