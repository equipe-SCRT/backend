package school.sptech.backendscrt.Model;

public class Endereco {
    private int idEndereco;
    private String logradouro;
    private String bairro;
    private String numero;
    private String cep;
    private int fkRua;

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

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
