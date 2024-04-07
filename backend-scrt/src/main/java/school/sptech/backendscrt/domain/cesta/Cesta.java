package school.sptech.backendscrt.domain.cesta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
@Entity
public class Cesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
