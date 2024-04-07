package school.sptech.backendscrt.domain.rua;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import school.sptech.backendscrt.domain.endereco.Endereco;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Rua {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRua;
    private String nomeRua;
    private List<Endereco> enderecos = new ArrayList<>();
    private int fkRota;

    public int getIdRua() {
        return idRua;
    }

    public void setIdRua(int idRua) {
        this.idRua = idRua;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public int getFkRota() {
        return fkRota;
    }

    public void setFkRota(int fkRota) {
        this.fkRota = fkRota;
    }

    public void adicionarEndereco(Endereco endereco) {
        enderecos.add(endereco);
    }
}
