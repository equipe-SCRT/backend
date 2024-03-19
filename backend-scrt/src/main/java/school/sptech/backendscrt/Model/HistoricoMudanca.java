package school.sptech.backendscrt.Model;

import java.time.LocalDate;

public class HistoricoMudanca {
    private int idHistoricoMudanca;
    private LocalDate dataHora;
    private int fkUsuario;

    public int getIdHistoricoMudanca() {
        return idHistoricoMudanca;
    }

    public void setIdHistoricoMudanca(int idHistoricoMudanca) {
        this.idHistoricoMudanca = idHistoricoMudanca;
    }

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

}
