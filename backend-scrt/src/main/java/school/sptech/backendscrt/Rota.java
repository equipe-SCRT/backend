package school.sptech.backendscrt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Rota {
    private int idRota;
    private String nomeRota;
    private LocalDate dataCriacao = LocalDate.now();
    private List<Endereco> enderecos = new ArrayList<>();

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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public void adicionarEndereco(Endereco endereco) {
        enderecos.add(endereco);
    }
}
