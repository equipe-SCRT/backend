package school.sptech.backendscrt.service.cesta.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public class CestaCriacaoDto {
    @PastOrPresent
    private LocalDate dataMontagem;
    @PositiveOrZero
    private int fkHistoricoRota;
    @PositiveOrZero
    private int fkLote;
    @PositiveOrZero
    private int fkTipoCesta;

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
