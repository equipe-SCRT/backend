package school.sptech.backendscrt.Model;

public class Condominio {
    private int idCondominio;
    private String nomeComdominio;
    private int fkEndereco;

    public int getIdCondominio() {
        return idCondominio;
    }

    public void setIdCondominio(int idCondominio) {
        this.idCondominio = idCondominio;
    }

    public String getNomeComdominio() {
        return nomeComdominio;
    }

    public void setNomeComdominio(String nomeComdominio) {
        this.nomeComdominio = nomeComdominio;
    }

    public int getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(int fkEndereco) {
        this.fkEndereco = fkEndereco;
    }
}
