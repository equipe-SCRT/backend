package school.sptech.backendscrt.domain.usuarioMudanca;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
@Entity
public class HistoricoMudanca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
