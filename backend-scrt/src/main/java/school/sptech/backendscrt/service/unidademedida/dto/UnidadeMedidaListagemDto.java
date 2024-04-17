package school.sptech.backendscrt.service.unidademedida.dto;

public class UnidadeMedidaListagemDto {
    private Integer id;
    private String nome;
    private String representacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRepresentacao() {
        return representacao;
    }

    public void setRepresentacao(String representacao) {
        this.representacao = representacao;
    }
}
