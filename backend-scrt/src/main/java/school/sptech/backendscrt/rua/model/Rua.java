package school.sptech.backendscrt.rua.model;

import school.sptech.backendscrt.endereco.model.Endereco;

import java.util.ArrayList;
import java.util.List;

public class Rua {
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
