package school.sptech.backendscrt.domain.historicoRota;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;
@Entity
public class HistoricoRota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistoricoRota;
    private String kmRodados;
    private int qtdColaboradores;
    private LocalDate dataRota;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private int qtdCestas;
    private int fkRota;
    private int fkLote;

    public int getIdHistoricoRota() {
        return idHistoricoRota;
    }

    public void setIdHistoricoRota(int idHistoricoRota) {
        this.idHistoricoRota = idHistoricoRota;
    }

    public String getKmRodados() {
        return kmRodados;
    }

    public void setKmRodados(String kmRodados) {
        this.kmRodados = kmRodados;
    }

    public int getQtdColaboradores() {
        return qtdColaboradores;
    }

    public void setQtdColaboradores(int qtdColaboradores) {
        this.qtdColaboradores = qtdColaboradores;
    }

    public LocalDate getDataRota() {
        return dataRota;
    }

    public void setDataRota(LocalDate dataRota) {
        this.dataRota = dataRota;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public int getQtdCestas() {
        return qtdCestas;
    }

    public void setQtdCestas(int qtdCestas) {
        this.qtdCestas = qtdCestas;
    }

    public int getFkRota() {
        return fkRota;
    }

    public void setFkRota(int fkRota) {
        this.fkRota = fkRota;
    }

    public int getFkLote() {
        return fkLote;
    }

    public void setFkLote(int fkLote) {
        this.fkLote = fkLote;
    }
}
