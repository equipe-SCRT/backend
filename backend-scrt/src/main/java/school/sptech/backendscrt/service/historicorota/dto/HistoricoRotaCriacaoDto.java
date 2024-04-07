package school.sptech.backendscrt.service.historicorota.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class HistoricoRotaCriacaoDto {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String kmRodados;
    @NotNull
    @PositiveOrZero
    private int qtdColaboradores;
    @FutureOrPresent
    @NotNull
    private LocalDate dataRota;
    @FutureOrPresent
    @NotNull
    private LocalTime horaInicio;
    @FutureOrPresent
    @NotNull
    private LocalTime horaFim;
    @PositiveOrZero
    @NotNull
    private int qtdCestas;
    @PositiveOrZero
    @NotNull
    private int fkRota;
    @PositiveOrZero
    @NotNull
    private int fkLote;

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
