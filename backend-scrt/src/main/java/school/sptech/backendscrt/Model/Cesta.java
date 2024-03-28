package school.sptech.backendscrt.Model;

import java.time.LocalDate;

public class Cesta {
    private int idCesta;
    private LocalDate dataMontagem;
    private int fkHistoricoRota;
    private int fkLote;
    private int fkTipoCesta;

    public int getIdCesta() {
        return idCesta;
    }

    public void setIdCesta(int idCesta) {
        this.idCesta = idCesta;
    }

    public LocalDate getDataMontagem() {
        return dataMontagem;
    }

    public void setDataMontagem(LocalDate dataMontagem) {
        this.dataMontagem = dataMontagem;
    }

    public int getFkHistoricoRota() {
        return fkHistoricoRota;
    }

    public void setFkHistoricoRota(int fkHistoricoRota) {
        this.fkHistoricoRota = fkHistoricoRota;
    }

    public int getFkLote() {
        return fkLote;
    }

    public void setFkLote(int fkLote) {
        this.fkLote = fkLote;
    }

    public int getFkTipoCesta() {
        return fkTipoCesta;
    }

    public void setFkTipoCesta(int fkTipoCesta) {
        this.fkTipoCesta = fkTipoCesta;
    }
}
