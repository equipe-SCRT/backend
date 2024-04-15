package school.sptech.backendscrt.service.historicomudanca.dto;


import java.time.LocalDate;

public class HistoricoMudancaListagemDto {
    private int id;
    private LocalDate dataHora;
    private int fkUsuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
