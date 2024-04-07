package school.sptech.backendscrt.service.endereco.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class EnderecoCriacaoDto {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String logradouro;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String bairro;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String numero;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String cep;
    @PositiveOrZero
    private int fkRua;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getFkRua() {
        return fkRua;
    }

    public void setFkRua(int fkRua) {
        this.fkRua = fkRua;
    }
}
